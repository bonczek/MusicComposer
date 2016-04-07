package music.analysis.towsey.statistics;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.Note;
import music.Rest;
import music.Sound;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchVarietyStatisticTest {

    @Test
    public void testStatisticComputation() throws Exception {
        PitchVarietyStatistic statistic = new PitchVarietyStatistic(4);

        Note[] notes = {new Sound(127, Durations.SIXTEENTH_NOTE), new Sound(12, Durations.QUARTER_NOTE), new Sound
                (127, Durations.WHOLE_NOTE), new Rest(Durations.EIGHTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        for (Note note : noteList) {
            statistic.processNote(note);
        }
        assertThat(statistic.getResult(), is(0.5));
    }


}