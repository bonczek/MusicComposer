package music.analysis.feature.processor.statistics.intervals;

public class ContourDirectionFeatureTest extends IntervalFeatureTest<ContourDirectionStatistic> {

    @Override
    protected ContourDirectionStatistic initFeatureCounter() {
        return new ContourDirectionStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.625;
    }

}