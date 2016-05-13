package music.analysis.statistics.density;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class RestDensityStatisticTest {

    @Test
    public void testProcessNote() throws Exception {
        RestDensityStatistic statistic = new RestDensityStatistic();

        Note[] notes = {new Rest(Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithMidi(12), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithMidi(127), Durations.WHOLE_NOTE),
                new Rest(Durations.EIGHTH_NOTE),
                new Rest(Durations.SIXTEENTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        noteList.forEach(statistic::processNote);
        MatcherAssert.assertThat(statistic.getResult(), is(1.0 / 6.0));
    }
}