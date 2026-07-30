package io.beacon.testing.faults.response;

import io.beacon.testing.FaultHandler;

public class MarkdownJsonFault implements FaultHandler {

    @Override
    public String name() {
        return "Markdown JSON";
    }

    @Override
    public String apply(String validJson) {
        return """
                ```json
                %s
                ```
                """.formatted(validJson);
    }
}