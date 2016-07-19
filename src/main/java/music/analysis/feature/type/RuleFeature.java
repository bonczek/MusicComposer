package music.analysis.feature.type;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.factory.RuleProcessorFactory;

public class RuleFeature extends MelodicFeature<Double> {

    public RuleFeature(RuleName ruleName, double weight) {
        this.name = ruleName;
        this.featureWeight = weight;
        this.noteProcessor = RuleProcessorFactory.createRule(ruleName);
    }

    @Override
    public String getReport() {
        return String.format("%s - count: %f; weight: %f;", name, getFeatureResult(), getFeatureWeight());
    }
}
