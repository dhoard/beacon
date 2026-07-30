package io.beacon.testing.faults.response;

import io.beacon.testing.FaultHandler;

public class NoFault implements FaultHandler {
    @Override
    public String name() {
        return "No Fault";
    }

    @Override
    public String apply(String validJson) {
        return validJson;
    }
}
