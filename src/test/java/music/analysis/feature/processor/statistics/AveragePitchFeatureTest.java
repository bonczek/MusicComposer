package music.analysis.feature.processor.statistics;

import music.analysis.feature.processor.FeatureCounterTest;
import music.notes.pitch.Pitch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AveragePitchFeatureTest extends FeatureCounterTest<AveragePitchStatistic> {

    @Override
    protected AveragePitchStatistic initFeatureCounter() {
        return new AveragePitchStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return ((412.0 / 9.0) / (double) Pitch.MAX_MIDI_VALUE);
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getNumerator(), is(0.0));
        assertThat(featureCounter.getDenominator(), is((double) Pitch.MAX_MIDI_VALUE));
        assertThat(featureCounter.getMidiValueListSize(), is(0L));
    }
}