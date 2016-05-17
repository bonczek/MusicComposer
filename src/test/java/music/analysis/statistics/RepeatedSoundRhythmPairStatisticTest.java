package music.analysis.statistics;

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

public class RepeatedSoundRhythmPairStatisticTest {

    @Test
    public void testProcessNote() throws Exception {
        RepeatedSoundRhythmPairStatistic statistic = new RepeatedSoundRhythmPairStatistic();
        Note[] notes = {new Sound(Pitch.createWithMidi(127), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithMidi(12), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithMidi(127), Durations.WHOLE_NOTE),
                new Sound(Pitch.createWithMidi(12), Durations.WHOLE_NOTE),
                new Sound(Pitch.createWithMidi(127), Durations.WHOLE_NOTE),
                new Rest(Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        noteList.forEach(statistic::processNote);
        assertThat(statistic.getResult(), is(0.5));
    }
}