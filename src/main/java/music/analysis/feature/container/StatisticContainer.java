package music.analysis.feature.container;

import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.calculator.NormalDistributionStatisticCalculator;
import music.analysis.feature.type.StatisticalFeature;

import java.util.List;

public class StatisticContainer extends FeatureContainer<StatisticalFeature> {

    private static final FitnessCalculator<StatisticalFeature> FITNESS_CALCULATOR = new NormalDistributionStatisticCalculator();

    public StatisticContainer(List<StatisticalFeature> statistics) {
        super(statistics, FITNESS_CALCULATOR);
    }
}
