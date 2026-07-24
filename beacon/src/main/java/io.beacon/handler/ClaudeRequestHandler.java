package io.beacon.handler;

import com.sun.net.httpserver.HttpExchange;
import io.beacon.rules.RuleEngine;

import java.io.IOException;

public class ClaudeRequestHandler implements RequestHandler {

    private final RuleEngine ruleEngine;

    public ClaudeRequestHandler(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }
    @Override
    public boolean supports(HttpExchange exchange) {
        return exchange.getRequestURI()
                .getPath()
                .equals("/v1/chat/completions");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // parse request
        // execute rules
        // return OpenAI JSON

    }
}
