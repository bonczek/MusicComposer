package genetic.fitness.calculator;

import music.analysis.feature.type.StatisticalFeature;
import org.apache.commons.math3.distribution.NormalDistribution;

public class NormalDistributionStatisticCalculator implements FitnessCalculator<StatisticalFeature> {

    private static final double DISTRIBUTION_MEAN = 1.0;

    private static final int ACCURACY_MULTIPLIER = 2;

    private static final double DISTRIBUTION_STANDARD_DEVIATION = 0.5;

    private static final NormalDistribution REWARD_DISTRIBUTION = new NormalDistribution(DISTRIBUTION_MEAN, DISTRIBUTION_STANDARD_DEVIATION);

    /**
     * Transform result from melodic feature to reward used in GA.
     * Reward is computed using normal distribution with specified mean and standard deviation.
     * Difference between statistic result and expected value is subtracted from distribution mean.
     * Result is multiplied by accuracy multiplier and weight of given feature.
     *
     * @param statisticalFeature measured feature in melody line
     * @return reward/fitness value
     */
    @Override
    public int calculateReward(StatisticalFeature statisticalFeature) {
        double difference = Math.abs(statisticalFeature.getFeatureResult() - statisticalFeature.getExpectedValue());
        double accuracyFactor = ACCURACY_MULTIPLIER * REWARD_DISTRIBUTION.cumulativeProbability(DISTRIBUTION_MEAN - difference);
        return (int) (accuracyFactor * statisticalFeature.getFeatureWeight());
    }
}
