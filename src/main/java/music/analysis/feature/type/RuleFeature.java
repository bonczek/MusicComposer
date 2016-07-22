package music.analysis.feature.type;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.factory.RuleProcessorFactory;
import music.harmony.Harmony;

public class RuleFeature extends MelodicFeature<Double> {

    public RuleFeature(RuleName ruleName, double weight, Harmony scale) {
        this.name = ruleName;
        this.featureWeight = weight;
        this.noteProcessor = RuleProcessorFactory.createRule(ruleName, scale);
    }

    @Override
    public String getReport() {
        return String.format("%s - count: %f; weight: %f;", name, getFeatureResult(), getFeatureWeight());
    }
}
