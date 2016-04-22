package music;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntervalTest {

    @Test
    public void testInterval_givenMaxMidiRange() throws Exception {
        Sound firstNote = new Sound(127, 1.0);
        Sound secondNote = new Sound(0, 1.0);

        Interval interval = new Interval(firstNote, secondNote);
        assertThat(interval.getPitchInterval(), is(PitchInterval.PERFECT_FIFTH));
        assertThat(interval.getOctaveDifference(), is(-10));
        assertThat(interval.moreThanOctave(), is(true));
    }

    @Test
    public void testInterval_givenNotesInLessThanOctaveRange() throws Exception {
        Sound firstNote = new Sound(NoteName.F, Octave.FOUR_LINED, 1.0);
        Sound secondNote = new Sound(NoteName.D, Octave.FIFE_LINED, 1.0);

        Interval interval = new Interval(firstNote, secondNote);
        assertThat(interval.getPitchInterval(), is(PitchInterval.MAJOR_SIXTH));
        assertThat(interval.getOctaveDifference(), is(0));
        assertThat(interval.moreThanOctave(), is(false));
    }

    @Test
    public void testInterval_givenNotesInOctaveInterval() throws Exception {
        Sound firstNote = new Sound(NoteName.F_SHARP, Octave.GREAT, 1.0);
        Sound secondNote = new Sound(NoteName.F_SHARP, Octave.SMALL, 1.0);

        Interval interval = new Interval(firstNote, secondNote);
        assertThat(interval.getPitchInterval(), is(PitchInterval.PERFECT_UNISON));
        assertThat(interval.getOctaveDifference(), is(1));
        assertThat(interval.moreThanOctave(), is(false));
    }
}