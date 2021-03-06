package music.analysis.feature.processor.statistics;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.analysis.feature.processor.FeatureCounterTest;
import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchStandardDeviationFeatureTest extends FeatureCounterTest<PitchStandardDeviationStatistic> {

    @Override
    protected PitchStandardDeviationStatistic initFeatureCounter() {
        return new PitchStandardDeviationStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.452731179;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        assertThat(featureCounter.getNumerator(), is(0.0));
        assertThat(featureCounter.getDenominator(), is(PitchStandardDeviationStatistic.MAX_STANDARD_DEVIATION));
        assertThat(featureCounter.getMidiValueListSize(), is(0L));
    }

    @Test
    public void testProcessNote_givenMaxStdDeviation() throws Exception {
        Note[] notes = {
                new Sound(Pitch.createWithMidi(0), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithMidi(127), Durations.QUARTER_NOTE)
        };
        List<Note> noteList = Arrays.asList(notes);
        PitchStandardDeviationStatistic standardDeviationStatistic = new PitchStandardDeviationStatistic();
        noteList.forEach(standardDeviationStatistic::processNote);

        assertThat(standardDeviationStatistic.getResult(), is(1.0));
    }
}