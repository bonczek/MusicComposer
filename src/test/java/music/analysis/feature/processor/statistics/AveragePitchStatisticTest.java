package music.analysis.feature.processor.statistics;

import music.notes.pitch.Pitch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AveragePitchStatisticTest extends StatisticCounterTest<AveragePitchStatistic> {

    @Override
    protected AveragePitchStatistic initStatistic() {
        return new AveragePitchStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return ((412.0 / 9.0) / (double) Pitch.MAX_MIDI_VALUE);
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0.0));
        assertThat(statistic.getDenominator(), is((double) Pitch.MAX_MIDI_VALUE));
        assertThat(statistic.getMidiValueListSize(), is(0));
    }
}