package music.analysis.feature.processor.statistics.intervals;

public class DissonanceStatisticTest extends IntervalStatisticTest<DissonanceStatistic> {

    @Override
    protected DissonanceStatistic initStatistic() {
        return new DissonanceStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.25;
    }

}