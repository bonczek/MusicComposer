package music.analysis.statistics.intervals;

public class DissonanceStatisticTest extends IntervalStatisticTest<DissonanceStatistic> {

    @Override
    protected DissonanceStatistic initStatistic() {
        return new DissonanceStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.625;
    }

}