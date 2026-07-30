package io.beacon;

import io.beacon.testing.FaultHandler;
import io.beacon.testing.faults.response.EmptyResponseFault;
import io.beacon.testing.faults.response.InvalidJsonFault;
import io.beacon.testing.faults.response.MarkdownJsonFault;
import io.beacon.testing.faults.response.NoFault;
import io.beacon.testing.faults.response.TruncatedJsonFault;

public final class Fault {

    private Fault() {
    }

    public static FaultHandler none() {
        return new NoFault();
    }

    public static FaultHandler emptyResponse() {
        return new EmptyResponseFault();
    }

    public static FaultHandler invalidJson() {
        return new InvalidJsonFault();
    }

    public static FaultHandler truncatedJson() {
        return new TruncatedJsonFault();
    }

    public static FaultHandler markdownJson() {
        return new MarkdownJsonFault();
    }
}