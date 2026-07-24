package io.beacon.match;

public class AnyPromptMatcher implements PromptMatcher {

    @Override
    public boolean matches(String prompt) {
        return true;
    }

    @Override
    public int priority(){
        return 0;
    }
}
