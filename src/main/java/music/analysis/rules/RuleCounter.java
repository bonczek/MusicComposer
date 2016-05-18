package music.analysis.rules;

public abstract class RuleCounter implements MusicalRule {

    protected Integer ruleCounter = 0;

    @Override
    public int ruleCount() {
        return ruleCounter;
    }

    @Override
    public void clear() {
        ruleCounter = 0;
    }
}
