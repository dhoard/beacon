package io.beacon.match;

public interface PromptMatcher {

    boolean matches(String prompt);

    int priority();
}