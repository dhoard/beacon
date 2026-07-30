package io.beacon.testing.faults.response;

import io.beacon.testing.FaultHandler;

public class InvalidJsonFault implements FaultHandler {
    @Override
    public String name() {
        return "Invalid JSON";
    }

    @Override
    public String apply(String validJson) {
        if (validJson == null || validJson.length() <= 1) {
            return validJson;
        }

        return validJson.substring(0, validJson.length() - 1);
    }
}
