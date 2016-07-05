package music.analysis.feature.processor.statistics.intervals;

public class DiatonicStatisticTest extends IntervalStatisticTest<DiatonicStatistic> {

    @Override
    protected DiatonicStatistic initStatistic() {
        return new DiatonicStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.125;
    }
}