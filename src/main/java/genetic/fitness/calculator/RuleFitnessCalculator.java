package genetic.fitness.calculator;

import music.analysis.feature.type.RuleFeature;

public class RuleFitnessCalculator implements FitnessCalculator<RuleFeature> {

    @Override
    public int calculateReward(RuleFeature melodicFeature) {
        return (int) (melodicFeature.getFeatureResult() * melodicFeature.getFeatureWeight());
    }
}
