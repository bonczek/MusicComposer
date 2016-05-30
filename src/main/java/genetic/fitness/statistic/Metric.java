package genetic.fitness.statistic;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Metric {

    private static final double DISTRIBUTION_MEAN = 1.0;

    private static final int ACCURACY_MULTIPLIER = 2;

    private static final NormalDistribution REWARD_DISTRIBUTION = new NormalDistribution(DISTRIBUTION_MEAN, 0.5);

    private final MetricUnit metricUnit;
    private double difference;
    private double result;
    private int reward;

    public Metric(MetricUnit metricUnit) {
        this.metricUnit = metricUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric metric = (Metric) o;

        return metricUnit.equals(metric.metricUnit);
    }

    @Override
    public int hashCode() {
        return metricUnit.hashCode();
    }

    public int getReward() {
        return reward;
    }

    public void calculateReward(double statisticResult) {
        result = statisticResult;
        difference = Math.abs(statisticResult - metricUnit.getExpectedValue());
        double accuracyFactor = ACCURACY_MULTIPLIER * REWARD_DISTRIBUTION.cumulativeProbability(DISTRIBUTION_MEAN - difference);
        reward = (int) (accuracyFactor * metricUnit.getRewardWeight());
    }

    public String report() {
        return String.format("%s - Result: %f; Diff: %f; Reward: %d\n", metricUnit.getStatisticName(), result,
                difference, reward);
    }
}
