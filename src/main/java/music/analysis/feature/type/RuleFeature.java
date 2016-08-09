package music.analysis.feature.type;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.DoubleFeatureCounter;

public class RuleFeature extends MelodicFeature<Double> {

    public RuleFeature(RuleName ruleName, double weight, DoubleFeatureCounter noteProcessor) {
        super(ruleName, weight, noteProcessor);
    }

    @Override
    public String getReport() {
        return String.format("%s - count: %f; weight: %f;", name, getFeatureResult(), getFeatureWeight());
    }
}
