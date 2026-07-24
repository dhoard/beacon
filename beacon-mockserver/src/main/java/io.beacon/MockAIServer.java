package io.beacon;

import com.sun.net.httpserver.HttpServer;
import io.beacon.match.PromptMatcher;
import io.beacon.router.RequestRouter;
import io.beacon.rules.Rule;
import io.beacon.rules.RuleEngine;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class MockAIServer {

    private final HttpServer server;
    private final RuleEngine ruleEngine = new RuleEngine();
    private final RequestRouter router = new RequestRouter(ruleEngine);

    public MockAIServer() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(0), 0);
    }

    public MockAIServer(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
    }

    public void start() {

        server.createContext("/", router::route);

        server.start();

        System.out.println("Beacon Mock Server started at " + getBaseUrl());
    }

    public void stop() {
        server.stop(0);
    }

    public String getBaseUrl() {
        //InetSocketAddress address = server.getAddress();
        return "http://localhost:" + server.getAddress().getPort();
    }

    public WhenBuilder when(PromptMatcher matcher) {
        return new WhenBuilder(this, matcher);
    }

    public void addRule(PromptMatcher prompt, String response) {
        ruleEngine.addRule(prompt, response);
    }
}



