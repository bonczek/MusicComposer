package music.analysis.feature.processor.statistics.intervals;

public class ContourDirectionStatisticTest extends IntervalStatisticTest<ContourDirectionStatistic> {

    @Override
    protected ContourDirectionStatistic initStatistic() {
        return new ContourDirectionStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.625;
    }

}