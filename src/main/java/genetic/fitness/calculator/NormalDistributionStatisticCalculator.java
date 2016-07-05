package genetic.fitness.calculator;

import music.analysis.feature.type.StatisticalFeature;
import org.apache.commons.math3.distribution.NormalDistribution;

public class NormalDistributionStatisticCalculator implements FitnessCalculator<StatisticalFeature> {

    private static final double DISTRIBUTION_MEAN = 1.0;

    private static final int ACCURACY_MULTIPLIER = 2;

    private static final NormalDistribution REWARD_DISTRIBUTION = new NormalDistribution(DISTRIBUTION_MEAN, 0.5);

    @Override
    public int calculateReward(StatisticalFeature statisticalFeature) {
        double difference = Math.abs(statisticalFeature.getFeatureResult() - statisticalFeature.getExpectedValue());
        double accuracyFactor = ACCURACY_MULTIPLIER * REWARD_DISTRIBUTION.cumulativeProbability(DISTRIBUTION_MEAN - difference);
        return (int) (accuracyFactor * statisticalFeature.getFeatureWeight());
    }
}
