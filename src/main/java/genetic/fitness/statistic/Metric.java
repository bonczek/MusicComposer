package genetic.fitness.statistic;

public class Metric {

    private final MetricUnit metricUnit;
    private double difference;
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
        difference = Math.abs(statisticResult - metricUnit.getExpectedValue());
        reward = (int) (metricUnit.getRewardWeight() * Math.cos(Math.PI * difference));
    }

    public String report() {
        return String.format("%s - Diff: %f; Reward: %d\n", metricUnit.getStatisticName(), difference, reward);
    }
}
