package music.analysis.feature.processor.statistics.intervals;

public class IllegalJumpStatisticTest extends IntervalStatisticTest<IllegalJumpStatistic> {

    @Override
    protected IllegalJumpStatistic initStatistic() {
        return new IllegalJumpStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 5.0 / 8.0;
    }
}