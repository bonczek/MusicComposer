package genetic.fitness.statistic;

import genetic.fitness.Fitness;
import genetic.fitness.calculator.FitnessCalculator;
import music.analysis.feature.type.StatisticalFeature;

public class StatisticFitness extends Fitness {

    private StringBuilder reportBuilder = new StringBuilder();

    private FitnessCalculator<StatisticalFeature> fitnessCalculator;

    public StatisticFitness(FitnessCalculator<StatisticalFeature> fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }

    //@todo same method as in RuleFitness
    public void addMetricValues(StatisticalFeature metric) {
        double featureReward = fitnessCalculator.calculateReward(metric);
        reportBuilder.append(String.format("%s; reward: %f", metric.getReport(), featureReward));
        fitnessValue += fitnessCalculator.calculateReward(metric);
    }

    @Override
    public String getReport() {
        return reportBuilder.toString();
    }
}
