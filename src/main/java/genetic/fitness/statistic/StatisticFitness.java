package genetic.fitness.statistic;

import genetic.fitness.Fitness;

public class StatisticFitness extends Fitness {

    private StringBuilder reportBuilder = new StringBuilder();

    public void addMetricValues(Metric metric) {
        reportBuilder.append(metric.report());
        fitnessValue += metric.getReward();
    }

    @Override
    public String getReport() {
        return reportBuilder.toString();
    }
}
