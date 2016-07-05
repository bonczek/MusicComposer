package music.analysis.feature.processor.statistics;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RepeatedSoundRhythmPairStatisticTest extends StatisticCounterTest<RepeatedSoundRhythmPairStatistic> {
    @Override
    protected RepeatedSoundRhythmPairStatistic initStatistic() {
        return new RepeatedSoundRhythmPairStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.25;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getDenominator(), is(0));
        assertThat(statistic.getNumerator(), is(0));
        assertThat(statistic.getPreviousSound(), is(nullValue()));
    }

}