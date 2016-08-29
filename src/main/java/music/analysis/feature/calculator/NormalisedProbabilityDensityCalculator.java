package music.analysis.feature.calculator;

import music.analysis.feature.type.StatisticalFeature;

public class NormalisedProbabilityDensityCalculator implements RewardCalculator<StatisticalFeature> {

    @Override
    public int calculateReward(StatisticalFeature melodicFeature) {
        double density = calculateDensity(melodicFeature);
        return (int) (density * melodicFeature.getFeatureWeight());
    }

    private double calculateDensity(StatisticalFeature melodicFeature) {
        double diff = melodicFeature.getExpectedValue() - melodicFeature.getFeatureResult();
        double stDev = melodicFeature.getStandardDeviation();

        return Math.exp(-(diff * diff) / (2 * stDev * stDev));
    }
}
