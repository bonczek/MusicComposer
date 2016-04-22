package music.analysis.towsey.statistics;

import jm.constants.Durations;
import music.Harmony;
import music.Note;
import music.NoteName;
import music.Octave;
import music.Rest;
import music.Scale;
import music.Sound;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NonScaleStatisticTest {

    @Test
    public void testProcessNote() throws Exception {

        Note[] notes = {new Sound(NoteName.C, Octave.GREAT, Durations.HALF_NOTE), new Sound(NoteName.A_SHARP, Octave
                .CONTRA, Durations.QUARTER_NOTE), new Rest(Durations.QUARTER_NOTE), new Sound(NoteName.F_SHARP, Octave
                .ONE_LINED, Durations.WHOLE_NOTE)};

        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        TowseyStatistic statistic = new NonScaleStatistic(2, cMajorScale);
        for (Note note : notes) {
            statistic.processNote(note);
        }

        assertThat(statistic.getResult(), is(0.625));
    }
}