package music.analysis.feature.processor.rules;

public abstract class RuleCounter implements MusicalRule {

    protected Double ruleCounter = 0.0;

    @Override
    public Double getResult() {
        return ruleCounter;
    }

    @Override
    public void clear() {
        ruleCounter = 0.0;
    }
}
