package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SemitonesDifferenceStatisticTest extends IntervalStatisticTest<SemitonesDifferenceStatistic> {

    @Override
    protected SemitonesDifferenceStatistic initStatistic() {
        return new SemitonesDifferenceStatistic();
    }

    @Override
    protected double getExpectedResult() {
        return 0.3484;
    }

    @Test
    public void test() throws Exception {
        Pitch firstNote = Pitch.createWithMidi(12);
        Pitch secondNote = Pitch.createWithMidi(68);
        Interval interval = new Interval(firstNote, secondNote);
        SemitonesDifferenceStatistic statistic = new SemitonesDifferenceStatistic();

        statistic.processInterval(interval);

        assertThat(statistic.getNumerator(), is(secondNote.getMidiValue() - firstNote.getMidiValue()));
        assertThat(statistic.getDenominator(), is(Pitch.MAX_MIDI_VALUE));
    }
}