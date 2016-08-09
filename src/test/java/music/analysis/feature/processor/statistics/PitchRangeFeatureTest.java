package music.analysis.feature.processor.statistics;

import jm.constants.Durations;
import music.analysis.feature.processor.DoubleFeatureCounter;
import music.analysis.feature.processor.FeatureCounterTest;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchRangeFeatureTest extends FeatureCounterTest<PitchRangeStatistic> {

    @Override
    protected PitchRangeStatistic initFeatureCounter() {
        return new PitchRangeStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.7165354330708;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getNumerator(), is(0));
        assertThat(featureCounter.getDenominator(), is(Pitch.MAX_MIDI_VALUE));
        assertThat(featureCounter.getHighestPitch(), is(Integer.MIN_VALUE));
        assertThat(featureCounter.getLowestPitch(), is(Integer.MAX_VALUE));
    }

    @Test
    public void testProcessNote_givenRangeGreaterThanTwoOctaves() throws Exception {
        DoubleFeatureCounter statistic = new PitchRangeStatistic();
        Note[] notes = {new Sound(Pitch.createWithMidi(127), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithMidi(19), Durations.WHOLE_NOTE)};
        for (Note note : notes) {
            statistic.processNote(note);
        }
        assertThat(statistic.getResult(), is(0.8503937007874016));
    }
}