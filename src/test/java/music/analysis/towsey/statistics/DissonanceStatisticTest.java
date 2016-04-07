package music.analysis.towsey.statistics;

import jm.constants.Durations;
import music.Note;
import music.Rest;
import music.Sound;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DissonanceStatisticTest {

    @Test
    public void testStatisticComputation() throws Exception {
        DissonanceStatistic statistic = new DissonanceStatistic();

        Note[] notes = {new Sound(127, Durations.SIXTEENTH_NOTE), new Sound(12, Durations.QUARTER_NOTE), new Sound
                (13, Durations.WHOLE_NOTE), new Sound(19, Durations.WHOLE_NOTE), new Sound(30, Durations.WHOLE_NOTE),
                new Rest(Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        noteList.forEach(statistic::processNote);
        assertThat(statistic.getResult(), is(0.75));
    }

}