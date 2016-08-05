package music.analysis.feature.processor.rules;

import music.analysis.feature.processor.DoubleFeatureCounter;

public abstract class RuleCounter implements DoubleFeatureCounter {

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
