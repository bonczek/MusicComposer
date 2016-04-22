package music;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PitchTest {

    @Test
    public void testCreateWithNames() throws Exception {
        NoteName note = NoteName.D;
        Octave octave = Octave.GREAT;

        Pitch pitch = Pitch.createWithNames(note, octave);
        assertThat(pitch.getMidiValue(), is(38));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithNames_givenOutOfRangeValues() throws Exception {
        NoteName note = NoteName.A;
        Octave octave = Octave.SIX_LINED;

        Pitch.createWithNames(note, octave);
    }

    @Test
    public void testCreateWithMidi() throws Exception {
        int midiValue = 103;
        Pitch pitch = Pitch.createWithMidi(midiValue);
        assertThat(pitch.getNoteName(), is(NoteName.G));
        assertThat(pitch.getOctave(), is(Octave.FOUR_LINED));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithMidi_givenOutOfRangeValues() throws Exception {
        int midiValue = 160;
        Pitch.createWithMidi(midiValue);
    }

    @Test
    public void testCreateWithInterval() throws Exception {
        NoteName note = NoteName.D;
        Octave octave = Octave.GREAT;
        Pitch base = Pitch.createWithNames(note, octave);
        PitchInterval interval = PitchInterval.PERFECT_FIFTH;

        Pitch newPitch = Pitch.createWithInterval(base, interval);
        assertThat(newPitch.getOctave(), is(Octave.GREAT));
        assertThat(newPitch.getNoteName(), is(NoteName.A));
        assertThat(newPitch.getMidiValue(), is(45));
    }

    @Test
    public void testChangeNoteName() throws Exception {
        NoteName note = NoteName.D;
        Octave octave = Octave.GREAT;
        Pitch pitch = Pitch.createWithNames(note, octave);
        NoteName newNoteName = NoteName.B;

        pitch.changeNoteName(newNoteName);
        assertThat(pitch.getNoteName(), is(newNoteName));
        assertThat(pitch.getMidiValue(), is(47));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testChangeNoteName_givenOutOfRangeValue() throws Exception {
        NoteName note = NoteName.D;
        Octave octave = Octave.SIX_LINED;
        Pitch pitch = Pitch.createWithNames(note, octave);

        pitch.changeNoteName(NoteName.B);
    }

    @Test
    public void testChangeOctave() throws Exception {
        NoteName note = NoteName.D;
        Octave octave = Octave.GREAT;
        Pitch pitch = Pitch.createWithNames(note, octave);
        Octave newOctave = Octave.ONE_LINED;

        pitch.changeOctave(newOctave);
        assertThat(pitch.getOctave(), is(newOctave));
        assertThat(pitch.getMidiValue(), is(62));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testChangeOctave_givenOutOfRangeValue() throws Exception {
        NoteName note = NoteName.B;
        Octave octave = Octave.GREAT;
        Pitch pitch = Pitch.createWithNames(note, octave);
        Octave newOctave = Octave.SIX_LINED;

        pitch.changeOctave(newOctave);
    }

    @Test
    public void testMidiFromNotes() throws Exception {
        NoteName noteName = NoteName.F_SHARP;
        Octave octave = Octave.SIX_LINED;
        int midiValue = Pitch.midiFromNotes(noteName, octave);
        assertThat(midiValue, is(126));
    }
}