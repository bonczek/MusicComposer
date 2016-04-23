package music.analysis.towsey.statistics;

import jm.constants.Durations;
import music.Harmony;
import music.Scale;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NonScaleStatisticTest {

    @Test
    public void testProcessNote() throws Exception {

        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.C, Octave.GREAT), Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.CONTRA), Durations.QUARTER_NOTE), new Rest
                (Durations.QUARTER_NOTE), new Sound(Pitch.createWithNames(NoteName.F_SHARP, Octave.ONE_LINED),
                Durations.WHOLE_NOTE)};

        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        TowseyStatistic statistic = new NonScaleStatistic(2, cMajorScale);
        for (Note note : notes) {
            statistic.processNote(note);
        }

        assertThat(statistic.getResult(), is(0.625));
    }
}