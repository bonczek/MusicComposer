package genetic.fitness.statistic;

public class MetricUnit {

    private final Statistic statisticName;
    private final double expectedValue;
    private final double rewardWeight;

    public MetricUnit(Statistic statisticName, double expectedValue, double rewardWeight) {
        this.statisticName = statisticName;
        this.expectedValue = expectedValue;
        this.rewardWeight = rewardWeight;
    }

    public double getRewardWeight() {
        return rewardWeight;
    }

    public double getExpectedValue() {
        return expectedValue;
    }

    public Statistic getStatisticName() {
        return statisticName;
    }
}
