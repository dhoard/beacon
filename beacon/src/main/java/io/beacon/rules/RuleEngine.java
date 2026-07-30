package io.beacon.rules;

import io.beacon.Fault;
import io.beacon.match.PromptMatcher;
import io.beacon.testing.FaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    private static final List<Rule> rules = new ArrayList<>();

    public Rule addRule(PromptMatcher matcher, String response) {
        Rule rule = new Rule(matcher, response);
        rules.add(rule);
        return rule;
    }

    public static Rule findMatchingRule(String prompt) {

        Rule bestRule = null;

        for (int i = rules.size() - 1; i >= 0; i--) {

            Rule rule = rules.get(i);

            if (!rule.getMatcher().matches(prompt)) {
                continue;
            }

            if (bestRule == null ||
                    rule.getMatcher().priority() > bestRule.getMatcher().priority()) {
                bestRule = rule;
            }
        }

        return bestRule;
    }
}
