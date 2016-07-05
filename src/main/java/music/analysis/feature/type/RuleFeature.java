package music.analysis.feature.type;

import music.analysis.feature.factory.RuleProcessorFactory;
import music.analysis.feature.name.RuleName;

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
