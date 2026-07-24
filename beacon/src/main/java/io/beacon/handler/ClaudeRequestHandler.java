package io.beacon.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import io.beacon.rules.Rule;
import io.beacon.rules.RuleEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ClaudeRequestHandler implements RequestHandler {

    private final RuleEngine ruleEngine;

    public ClaudeRequestHandler(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }
    @Override
    public boolean supports(HttpExchange exchange) {
        return exchange.getRequestURI()
                .getPath()
                .equals("/v1/messages");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestBody = new String(exchange.getRequestBody().readAllBytes());

        System.out.println("Claude Request:");
        System.out.println(requestBody);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(requestBody);

        String prompt = root
                .path("messages")
                .get(root.path("messages").size() - 1)
                .path("content")
                .asText();

        System.out.println(prompt);

        String content = "I'm your mock AI assistant. No matching rule was found for this request.";
        Rule rule = RuleEngine.findMatchingRule(prompt);
        if(rule != null) {
            content = rule.response();
        }

        String response = """
{
  "id": "msg_123",
  "type": "message",
  "role": "assistant",
  "model": "claude-sonnet-4-20250514",
  "content": [
    {
      "type": "text",
      "text": "%s"
    }
  ],
  "stop_reason": "end_turn",
  "stop_sequence": null,
  "usage": {
    "input_tokens": 10,
    "output_tokens": 8
  }
}""".formatted(content);

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, bytes.length);

        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
