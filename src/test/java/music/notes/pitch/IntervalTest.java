package music.notes.pitch;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntervalTest {

    @Test
    public void testInterval_givenMaxMidiRange() throws Exception {
        Pitch firstNote = Pitch.createWithMidi(127);
        Pitch secondNote = Pitch.createWithMidi(0);

        Interval interval = new Interval(firstNote, secondNote);
        assertThat(interval.getPitchInterval(), is(PitchInterval.PERFECT_FIFTH));
        assertThat(interval.getOctaveDifference(), is(-10));
        assertThat(interval.moreThanOctave(), is(true));
    }

    @Test
    public void testInterval_givenNotesInLessThanOctaveRange() throws Exception {
        Pitch firstNote = Pitch.createWithNames(NoteName.F, Octave.FOUR_LINED);
        Pitch secondNote = Pitch.createWithNames(NoteName.D, Octave.FIFE_LINED);

        Interval interval = new Interval(firstNote, secondNote);
        assertThat(interval.getPitchInterval(), is(PitchInterval.MAJOR_SIXTH));
        assertThat(interval.getOctaveDifference(), is(0));
        assertThat(interval.moreThanOctave(), is(false));
    }

    @Test
    public void testInterval_givenNotesInOctaveInterval() throws Exception {
        Pitch firstNote = Pitch.createWithNames(NoteName.F_SHARP, Octave.GREAT);
        Pitch secondNote = Pitch.createWithNames(NoteName.F_SHARP, Octave.SMALL);

        Interval interval = new Interval(firstNote, secondNote);
        assertThat(interval.getPitchInterval(), is(PitchInterval.PERFECT_UNISON));
        assertThat(interval.getOctaveDifference(), is(1));
        assertThat(interval.moreThanOctave(), is(false));
    }
}