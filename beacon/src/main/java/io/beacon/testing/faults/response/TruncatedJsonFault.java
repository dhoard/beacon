package io.beacon.testing.faults.response;

import io.beacon.testing.FaultHandler;

public class TruncatedJsonFault implements FaultHandler {

    @Override
    public String name() {
        return "Truncated JSON";
    }

    @Override
    public String apply(String validJson) {

        if (validJson == null || validJson.length() < 10) {
            return validJson;
        }

        int truncateAt = (int) (validJson.length() * 0.8);

        return validJson.substring(0, truncateAt);
    }
}