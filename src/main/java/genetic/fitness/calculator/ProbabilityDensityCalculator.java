package genetic.fitness.calculator;

import music.analysis.feature.type.StatisticalFeature;
import org.apache.commons.math3.distribution.NormalDistribution;

public class ProbabilityDensityCalculator implements FitnessCalculator<StatisticalFeature> {

    @Override
    public int calculateReward(StatisticalFeature melodicFeature) {
        NormalDistribution rewardDistribution = new NormalDistribution(melodicFeature.getExpectedValue(),
                melodicFeature.getStandardDeviation());
        double density = rewardDistribution.density(melodicFeature.getFeatureResult());
        return (int) (density * melodicFeature.getFeatureWeight());
    }
}
