package music.analysis.feature.processor.statistics.intervals;

public class IllegalJumpFeatureTest extends IntervalFeatureTest<IllegalJumpStatistic> {

    @Override
    protected IllegalJumpStatistic initFeatureCounter() {
        return new IllegalJumpStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 5.0 / 8.0;
    }
}