package music.analysis.feature.processor.statistics;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RhythmVarietyStatisticTest extends StatisticCounterTest<RhythmVarietyStatistic> {

    @Override
    protected RhythmVarietyStatistic initStatistic() {
        return new RhythmVarietyStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 5.0 / 11.0;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getDenominator(), is(0));
        assertThat(statistic.getNumerator(), is(0));
        assertThat(statistic.getRhythmSetSize(), is(0));
    }
}