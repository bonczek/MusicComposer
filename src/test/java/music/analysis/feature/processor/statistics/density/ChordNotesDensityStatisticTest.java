package music.analysis.feature.processor.statistics.density;

import music.analysis.feature.processor.statistics.StatisticCounterTest;
import music.analysis.util.ChordProgressionData;
import music.analysis.util.MelodyData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChordNotesDensityStatisticTest extends StatisticCounterTest<ChordNotesDensityStatistic> {

    @Override
    protected ChordNotesDensityStatistic initStatistic() {
        return new ChordNotesDensityStatistic(ChordProgressionData.prepareFourMeasuresGAndCMajor());
    }

    @Override
    protected double getExpectedResult() {
        return 0.203125;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(statistic.getNumerator(), is(0.0));
        assertThat(statistic.getDenominator(), is(0.0));
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

}