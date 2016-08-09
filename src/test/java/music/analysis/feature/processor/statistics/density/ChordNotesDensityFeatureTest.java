package music.analysis.feature.processor.statistics.density;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.util.ChromosomeData;
import genetic.util.Converter;
import music.analysis.feature.processor.FeatureCounterTest;
import music.analysis.util.ChordProgressionData;
import music.analysis.util.MelodyData;
import music.notes.Note;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChordNotesDensityFeatureTest extends FeatureCounterTest<ChordNotesDensityStatistic> {

    @Override
    protected ChordNotesDensityStatistic initFeatureCounter() {
        return new ChordNotesDensityStatistic(ChordProgressionData.prepareFourMeasuresGAndCMajor());
    }

    @Override
    protected double getExpectedResult() {
        return 0.203125;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getNumerator(), is(0.0));
        assertThat(featureCounter.getDenominator(), is(0.0));
    }

    @Test
    public void testCalculateTimeFittingHarmony() throws Exception {
        ChordNotesDensityStatistic chordNotesStatistic =
                new ChordNotesDensityStatistic(ChordProgressionData.prepareThreeMeasuresOfChordProgression());
        MelodyData.prepareThreeMeasuresOfMelody().forEach(chordNotesStatistic::processNote);

        assertThat(chordNotesStatistic.getDenominator(), is(12.0));
        assertThat(chordNotesStatistic.getNumerator(), is(5.0));

    }

    @Test
    public void testCalculateTimeFittingHarmony_givenShortNotesAndLongChordDurations() throws Exception {
        ChordNotesDensityStatistic chordNotesStatistic =
                new ChordNotesDensityStatistic(ChordProgressionData.prepareTwoMeasuresChordProgressionWithLongDuration());
        MelodyData.prepareTwoMeasuresWithShortNotes().forEach(chordNotesStatistic::processNote);

        assertThat(chordNotesStatistic.getDenominator(), is(8.0));
        assertThat(chordNotesStatistic.getNumerator(), is(1.0));
    }

    @Test
    public void testCalculateTimeFittingHarmony_givenLotOfChordChanges() throws Exception {
        ChordNotesDensityStatistic chordNotesStatistic =
                new ChordNotesDensityStatistic(ChordProgressionData
                        .prepareTwoMeasuresOfChordProgressionWithShortDurations());
        MelodyData.prepareTwoMeasuresWithLongNotes().forEach(chordNotesStatistic::processNote);

        assertThat(chordNotesStatistic.getDenominator(), is(8.0));
        assertThat(chordNotesStatistic.getNumerator(), is(3.25));
    }

    @Test
    public void testProcessNote_givenIrregularNoteAndChordEndings() throws Exception {
        ChordNotesDensityStatistic chordNotesStatistic =
                new ChordNotesDensityStatistic(ChordProgressionData
                        .prepareTwoMeasuresChordProgressionWithLongDurationBreakingSchema());
        MelodyData.prepareTwoMeasuresWithLongNotesWithoutMeasureSchema().forEach(chordNotesStatistic::processNote);

        assertThat(chordNotesStatistic.getDenominator(), is(8.0));
        assertThat(chordNotesStatistic.getNumerator(), is(3.75));
    }

    @Test
    public void testProcessNote_givenOmittedCase() throws Exception {
        Integer[] values = {89, 3, 62, 12, 51, 87, 45, 68, 55, 40, 61, 86, 2, 68, 89, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, 126
                , 10, 112, 102, 94, 80, 99, 69, 77, 19, 106, 96, 97, 21, 109, 66, 64, -2, 104, 71, 61, 120, 13, 76, 68, 108, 107, 100, 22, 41, 40,
                119, 29, 52, 7};
        List<Note> noteList = Converter.fromChromosome(ChromosomeData.createWithIntegerValues(Arrays.asList(values)));

        ChordNotesDensityStatistic chordNotesStatistic =
                new ChordNotesDensityStatistic(ChordProgressionData.prepareFourMeasuresGCDC());

        noteList.forEach(chordNotesStatistic::processNote);

        assertThat(chordNotesStatistic.getDenominator(), is(16.0));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testProcessNote_givenLongerMelodyThanProgression() throws Exception {
        ChordNotesDensityStatistic chordNotesStatistic =
                new ChordNotesDensityStatistic(ChordProgressionData.prepareTwoMeasuresChordProgressionWithLongDuration());

        MelodyData.prepareFourMeasureSample().forEach(chordNotesStatistic::processNote);

        assertThat(chordNotesStatistic.getDenominator(), is(16.0));
    }

}