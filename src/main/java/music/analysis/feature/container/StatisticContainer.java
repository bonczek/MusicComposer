package music.analysis.feature.container;

import music.analysis.feature.calculator.NormalisedProbabilityDensityCalculator;
import music.analysis.feature.calculator.RewardCalculator;
import music.analysis.feature.type.StatisticalFeature;

import java.util.List;

public class StatisticContainer extends FeatureContainer<StatisticalFeature> {

    private static final RewardCalculator<StatisticalFeature> NORMAL_DISTRIBUTION_CALCULATOR = new
            NormalisedProbabilityDensityCalculator();

    public StatisticContainer(List<StatisticalFeature> statistics) {
        super(statistics, NORMAL_DISTRIBUTION_CALCULATOR);
    }
}
