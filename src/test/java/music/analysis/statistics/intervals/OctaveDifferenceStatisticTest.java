package music.analysis.statistics.intervals;

public class OctaveDifferenceStatisticTest extends IntervalStatisticTest<OctaveDifferenceStatistic> {

    @Override
    protected OctaveDifferenceStatistic initStatistic() {
        return new OctaveDifferenceStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.2840909;
    }
}