package music.analysis.feature.type;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.factory.RuleProcessorFactory;

public class RuleFeature extends MelodicFeature<Integer> {

    public RuleFeature(RuleName ruleName, int weight) {
        this.name = ruleName;
        this.featureWeight = weight;
        this.noteProcessor = RuleProcessorFactory.createRule(ruleName);
    }

    @Override
    public String getReport() {
        return String.format("%s - count: %d; weight: %d;", name, getFeatureResult(), getFeatureWeight());
    }
}
