package io.beacon.rules;

import io.beacon.match.PromptMatcher;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    private static final List<Rule> rules = new ArrayList<>();

    public void addRule(PromptMatcher matcher, String response) {
        rules.add(new Rule(matcher, response));
    }

    public static Rule findMatchingRule(String prompt) {

        Rule bestRule = null;

        for (int i = rules.size() - 1; i >= 0; i--) {

            Rule rule = rules.get(i);

            if (!rule.matcher().matches(prompt)) {
                continue;
            }

            if (bestRule == null ||
                    rule.matcher().priority() > bestRule.matcher().priority()) {
                bestRule = rule;
            }
        }

        return bestRule;
    }
}
