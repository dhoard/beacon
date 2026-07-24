package io.beacon;

import io.beacon.match.PromptMatcher;

public class WhenBuilder {

    private final MockAIServer server;
    private final PromptMatcher matcher;

    public WhenBuilder(MockAIServer server,
                       PromptMatcher matcher) {
        this.server = server;
        this.matcher = matcher;
    }

    public void respondWith(String response) {
        server.addRule(this.matcher, response);
    }
}
