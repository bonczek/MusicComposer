package music.analysis.feature.processor.statistics.intervals;

public class DiatonicFeatureTest extends IntervalFeatureTest<DiatonicStatistic> {

    @Override
    protected DiatonicStatistic initFeatureCounter() {
        return new DiatonicStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.125;
    }

    //@todo test with diatonic intervals between octaves
}