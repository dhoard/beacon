package io.beacon.match;

public class ContainsPromptMatcher implements PromptMatcher {
    private String text;
    public ContainsPromptMatcher(String text) {
        this.text = text;
    }

    @Override
    public boolean matches(String text){
        return text.contains(this.text);
    }

    @Override
    public int priority() {
        return 10;
    }
}
