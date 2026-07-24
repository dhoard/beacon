package io.beacon;

import io.beacon.match.AnyPromptMatcher;
import io.beacon.match.ContainsPromptMatcher;
import io.beacon.match.PromptMatcher;

public final class PromptMatchers {

    public static PromptMatcher contains(String text) {
        return new ContainsPromptMatcher(text);
    }

    public static PromptMatcher any() {
        return new AnyPromptMatcher();
    }

}
