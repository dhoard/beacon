package io.beacon.testing.faults.response;

import io.beacon.testing.FaultHandler;

public class EmptyResponseFault implements FaultHandler {

    @Override
    public String name() {
        return "Empty Response";
    }

    @Override
    public String apply(String response) {
        return "";
    }
}
