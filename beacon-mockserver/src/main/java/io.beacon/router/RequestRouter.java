package io.beacon.router;

import com.sun.net.httpserver.HttpExchange;
import io.beacon.handler.ClaudeRequestHandler;
import io.beacon.handler.GeminiRequestHandler;
import io.beacon.handler.OpenAIRequestHandler;
import io.beacon.handler.RequestHandler;
import io.beacon.rules.RuleEngine;

import java.io.IOException;
import java.util.*;

public class RequestRouter {


    private final List<RequestHandler> handlers;

    public RequestRouter(RuleEngine ruleEngine) {
        this.handlers = List.of(
                new OpenAIRequestHandler(ruleEngine),
                new ClaudeRequestHandler(ruleEngine),
                new GeminiRequestHandler(ruleEngine)
        );
    }

    public void route(HttpExchange exchange) throws IOException {

        for (RequestHandler handler : handlers) {
            if (handler.supports(exchange)) {
                handler.handle(exchange);
                return;
            }
        }

        exchange.sendResponseHeaders(404, -1);
        exchange.close();
    }
}
