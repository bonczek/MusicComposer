package music.analysis.feature.type;

import music.analysis.feature.name.RuleName;
import music.analysis.feature.processor.factory.RuleProcessorFactory;
import music.harmony.Chord;
import music.harmony.Harmony;

import java.util.List;

public class RuleFeature extends MelodicFeature<Double> {

    public RuleFeature(RuleName ruleName, double weight, Harmony scale, List<Chord> chordList) {
        this.name = ruleName;
        this.featureWeight = weight;
        this.noteProcessor = RuleProcessorFactory.createRule(ruleName, scale, chordList);
    }

    @Override
    public String getReport() {
        return String.format("%s - count: %f; weight: %f;", name, getFeatureResult(), getFeatureWeight());
    }
}
