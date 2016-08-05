package music.analysis.feature.container;

import genetic.fitness.calculator.CumulativeDistributionCalculator;
import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.calculator.ProbabilityDensityCalculator;
import music.analysis.feature.type.StatisticalFeature;

import java.util.List;

public class StatisticContainer extends FeatureContainer<StatisticalFeature> {

    private static final FitnessCalculator<StatisticalFeature> FITNESS_CALCULATOR = new CumulativeDistributionCalculator();

    private static final FitnessCalculator<StatisticalFeature> NORMAL_DISTRIBUTION_CALCULATOR = new
            ProbabilityDensityCalculator();

    public StatisticContainer(List<StatisticalFeature> statistics) {
        super(statistics, NORMAL_DISTRIBUTION_CALCULATOR);
    }
}
