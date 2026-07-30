package io.beacon;

import io.beacon.match.PromptMatcher;
import io.beacon.rules.Rule;

public class WhenBuilder {

    private final MockAIServer server;
    private final PromptMatcher matcher;
    private ResponseBuilder responseBuilder;

    public WhenBuilder(MockAIServer server, PromptMatcher matcher) {
        this.server = server;
        this.matcher = matcher;
        this.responseBuilder = null;
    }

    public ResponseBuilder respondWith(String response) {
        Rule rule = server.addRule(this.matcher, response);
        this.responseBuilder = new ResponseBuilder(rule);
        return this.responseBuilder;
    }
}
