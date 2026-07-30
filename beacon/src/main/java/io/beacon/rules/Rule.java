package io.beacon.rules;

import io.beacon.Fault;
import io.beacon.match.PromptMatcher;
import io.beacon.testing.FaultHandler;

public class Rule {
    private final PromptMatcher matcher;
    private final String response;
    private FaultHandler fault = Fault.none();

    public Rule(PromptMatcher matcher, String response) {
        this.matcher = matcher;
        this.response = response;
    }

    public PromptMatcher getMatcher() {
        return matcher;
    }

    public String getResponse() {
        return response;
    }

    public FaultHandler getFault() {
        return fault;
    }

    public void setFault(FaultHandler fault) {
        this.fault = fault;
    }
}
