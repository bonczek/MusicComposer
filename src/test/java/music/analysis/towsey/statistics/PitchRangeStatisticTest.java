package music.analysis.towsey.statistics;

import jm.constants.Durations;
import music.Note;
import music.Rest;
import music.Sound;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchRangeStatisticTest {

    @Test
    public void testProcessNote() throws Exception {
        TowseyStatistic statistic = new PitchRangeStatistic(24);
        Note[] notes = {new Sound(31, Durations.QUARTER_NOTE), new Rest(Durations.SIXTEENTH_NOTE), new Sound(19,
                Durations.WHOLE_NOTE)};
        for (Note note : notes) {
            statistic.processNote(note);
        }
        assertThat(statistic.getResult(), is(0.5));
    }

    @Test
    public void testProcessNote_givenRangeGreaterThanTwoOctaves() throws Exception {
        TowseyStatistic statistic = new PitchRangeStatistic(24);
        Note[] notes = {new Sound(127, Durations.QUARTER_NOTE), new Sound(19, Durations.WHOLE_NOTE)};
        for (Note note : notes) {
            statistic.processNote(note);
        }
        assertThat(statistic.getResult(), is(1.0));
    }
}