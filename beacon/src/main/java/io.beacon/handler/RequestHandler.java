package io.beacon.handler;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public interface RequestHandler {

    boolean supports(HttpExchange exchange);

    void handle(HttpExchange exchange) throws IOException;

}