package music.analysis.feature.processor.statistics.intervals;

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

public class RepeatedIntervalStatisticTest extends IntervalStatisticTest<RepeatedIntervalStatistic> {

    @Test
    public void testProcessInterval() throws Exception {
        RepeatedIntervalStatistic statistic = new RepeatedIntervalStatistic();
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

    @Override
    protected RepeatedIntervalStatistic initStatistic() {
        return new RepeatedIntervalStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.125;
    }

    @Override
    protected void afterClearAsserts() throws Exception {
        super.afterClearAsserts();
        assertThat(statistic.getIntervalSetSize(), is(0));
    }
}