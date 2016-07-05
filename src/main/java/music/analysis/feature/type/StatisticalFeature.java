package music.analysis.feature.type;

import music.Harmony;
import music.analysis.feature.factory.StatisticProcessorFactory;
import music.analysis.feature.name.StatisticName;

public class StatisticalFeature extends MelodicFeature<Double> {

    private final double expectedValue;

    public StatisticalFeature(StatisticName statisticName, double expectedValue, double statisticWeight, Harmony scale) {
        this.name = statisticName;
        this.expectedValue = expectedValue;
        this.featureWeight = statisticWeight;
        this.noteProcessor = StatisticProcessorFactory.createStatistic(statisticName, scale);
    }

    public double getExpectedValue() {
        return expectedValue;
    }

    @Override
    public String getReport() {
        double difference = Math.abs(noteProcessor.getResult() - expectedValue);
        return String.format("%s - Result: %f; expected: %f; Diff: %f; weight: %f;", name, noteProcessor.getResult(),
                expectedValue, difference, featureWeight);
    }
}
