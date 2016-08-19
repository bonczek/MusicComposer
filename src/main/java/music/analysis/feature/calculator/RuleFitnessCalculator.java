package music.analysis.feature.calculator;

import music.analysis.feature.type.RuleFeature;

public class RuleFitnessCalculator implements RewardCalculator<RuleFeature> {

    @Override
    public int calculateReward(RuleFeature melodicFeature) {
        return (int) (melodicFeature.getFeatureResult() * melodicFeature.getFeatureWeight());
    }
}
