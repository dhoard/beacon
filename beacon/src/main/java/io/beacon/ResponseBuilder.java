package io.beacon;

import io.beacon.rules.Rule;
import io.beacon.testing.FaultHandler;

public class ResponseBuilder {

    //private final String response;
    private final Rule rule;

    public ResponseBuilder(Rule rule) {
        this.rule = rule;
    }

    public void respondFault(FaultHandler fault) {
        rule.setFault(fault);
    }

    public String getResponse() {
        return rule.getResponse();
    }

}