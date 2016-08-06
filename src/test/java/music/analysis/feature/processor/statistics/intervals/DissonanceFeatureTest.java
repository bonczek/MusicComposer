package music.analysis.feature.processor.statistics.intervals;

public class DissonanceFeatureTest extends IntervalFeatureTest<DissonanceStatistic> {

    @Override
    protected DissonanceStatistic initFeatureCounter() {
        return new DissonanceStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 5.0 / 8.0;
    }

}