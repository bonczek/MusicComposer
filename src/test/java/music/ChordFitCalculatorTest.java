package music;

import music.analysis.util.ChordProgressionData;
import music.analysis.util.MelodyData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChordFitCalculatorTest {

    @Test
    public void testCalculateTimeFittingHarmony() throws Exception {
        ChordFitCalculator chordFitCalculator =
                new ChordFitCalculator(ChordProgressionData.prepareThreeMeasuresOfChordProgression());

        double time = chordFitCalculator.calculateTimeFittingHarmony(MelodyData.prepareThreeMeasuresOfMelody());
        assertThat(chordFitCalculator.getTime(), is(12.0));
        assertThat(time, is(5.0));
    }

    @Test
    public void testCalculateTimeFittingHarmony_givenShortNotesAndLongChordDurations() throws Exception {
        ChordFitCalculator chordFitCalculator =
                new ChordFitCalculator(ChordProgressionData.prepareTwoMeasuresChordProgressionWithLongDuration());

        double time = chordFitCalculator.calculateTimeFittingHarmony(MelodyData.prepareTwoMeasuresWithShortNotes());
        assertThat(chordFitCalculator.getTime(), is(8.0));
        assertThat(time, is(1.0));
    }

    @Test
    public void testCalculateTimeFittingHarmony_givenLotOfChordChanges() throws Exception {
        ChordFitCalculator chordFitCalculator = new ChordFitCalculator(ChordProgressionData
                .prepareTwoMeasuresOfChordProgressionWithShortDurations());

        double time = chordFitCalculator.calculateTimeFittingHarmony(MelodyData.prepareTwoMeasuresWithLongNotes());
        assertThat(chordFitCalculator.getTime(), is(8.0));
        assertThat(time, is(3.25));
    }


}