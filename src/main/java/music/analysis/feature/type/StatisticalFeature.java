package music.analysis.feature.type;

import music.analysis.feature.name.StatisticName;
import music.analysis.feature.processor.MelodicFeatureCounter;

public class StatisticalFeature extends MelodicFeature<Double> {

    private final double expectedValue;

    public StatisticalFeature(StatisticName statisticName, double expectedValue, double statisticWeight,
                              MelodicFeatureCounter<Double> noteProcessor) {
        super(statisticName, statisticWeight, noteProcessor);
        this.expectedValue = expectedValue;
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
