package io.beacon.rules;

import io.beacon.match.PromptMatcher;

public record Rule(
        PromptMatcher matcher,
        String response
) {}
