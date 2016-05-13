package music.analysis.towsey.statistics;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NoteNameRangeStatisticTest {

    @Test
    public void testProcessNote() throws Exception {
        MusicalStatistic statistic = new PitchRangeStatistic(24);
        Note[] notes = {new Sound(Pitch.createWithMidi(31), Durations.QUARTER_NOTE), new Rest(Durations
                .SIXTEENTH_NOTE), new Sound(Pitch.createWithMidi(19), Durations.WHOLE_NOTE)};
        for (Note note : notes) {
            statistic.processNote(note);
        }
        assertThat(statistic.getResult(), is(0.5));
    }

    @Test
    public void testProcessNote_givenRangeGreaterThanTwoOctaves() throws Exception {
        MusicalStatistic statistic = new PitchRangeStatistic(24);
        Note[] notes = {new Sound(Pitch.createWithMidi(127), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithMidi(19), Durations.WHOLE_NOTE)};
        for (Note note : notes) {
            statistic.processNote(note);
        }
        assertThat(statistic.getResult(), is(1.0));
    }
}