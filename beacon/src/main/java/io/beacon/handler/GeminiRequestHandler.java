package io.beacon.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import io.beacon.rules.Rule;
import io.beacon.rules.RuleEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GeminiRequestHandler implements RequestHandler {

    private final RuleEngine ruleEngine;

    public GeminiRequestHandler(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }
    @Override
    public boolean supports(HttpExchange exchange) {

        return exchange.getRequestURI()
                .getPath()
                .equals("/v1beta/models/gemini-2.5-flash:generateContent");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestBody = new String(exchange.getRequestBody().readAllBytes());

        System.out.println("Gemini Request:");
        System.out.println(requestBody);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(requestBody);

        String prompt = root.path("contents")
                .path(0)
                .path("parts")
                .path(0)
                .path("text")
                .asText();

        System.out.println(prompt);

        String content = "I'm your mock AI assistant. No matching rule was found for this request.";
        Rule rule = RuleEngine.findMatchingRule(prompt);
        if(rule != null) {
            content = rule.getFault().apply(rule.getResponse());
        }

        String escapedContent = mapper.writeValueAsString(content);

        String response = """
{
  "candidates": [
    {
      "content": {
        "role": "model",
        "parts": [
          {
            "text": %s
          }
        ]
      },
      "finishReason": "STOP",
      "index": 0
    }
  ]
}""".formatted(escapedContent);

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, bytes.length);

        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
