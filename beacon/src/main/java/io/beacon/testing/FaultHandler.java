package io.beacon.testing;

public interface FaultHandler {

    String name();

    String apply(String validJson);
}