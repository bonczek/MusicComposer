package music.analysis.towsey.statistics;

import edu.emory.mathcs.backport.java.util.Arrays;
import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NoteNameVarietyStatisticTest {

    @Test
    public void testStatisticComputation() throws Exception {
        PitchVarietyStatistic statistic = new PitchVarietyStatistic();

        Note[] notes = {new Sound(Pitch.createWithMidi(127), Durations.SIXTEENTH_NOTE), new Sound(Pitch
                .createWithMidi(12), Durations.QUARTER_NOTE), new Sound(Pitch.createWithMidi(127),
                Durations.WHOLE_NOTE), new Rest(Durations.EIGHTH_NOTE), new Sound(Pitch.createWithMidi(52),
                Durations.SIXTEENTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        noteList.forEach(statistic::processNote);
        assertThat(statistic.getResult(), is(0.75));
    }


}