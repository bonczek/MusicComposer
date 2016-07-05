package music.analysis.feature.processor.rules;

public abstract class RuleCounter implements MusicalRule {

    protected Integer ruleCounter = 0;

    @Override
    public Integer getResult() {
        return ruleCounter;
    }

    @Override
    public void clear() {
        ruleCounter = 0;
    }
}
