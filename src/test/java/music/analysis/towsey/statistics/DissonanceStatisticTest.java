package music.analysis.towsey.statistics;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DissonanceStatisticTest {

    @Test
    public void testStatisticComputation() throws Exception {
        DissonanceStatistic statistic = new DissonanceStatistic();

        Note[] notes = {new Sound(Pitch.createWithMidi(127), Durations.SIXTEENTH_NOTE), new Sound(Pitch
                .createWithMidi(12), Durations.QUARTER_NOTE), new Sound(Pitch.createWithMidi(13),
                Durations.WHOLE_NOTE), new Sound(Pitch.createWithMidi(19), Durations.WHOLE_NOTE),
                new Sound(Pitch.createWithMidi(30), Durations.WHOLE_NOTE), new Rest(Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        noteList.forEach(statistic::processNote);
        assertThat(statistic.getResult(), is(0.75));
    }

}