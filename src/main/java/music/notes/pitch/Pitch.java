package music.notes.pitch;

import java.util.function.IntFunction;

/**
 * Pitch of the note restricted to midi range <0, 127>
 */
public class Pitch implements Comparable<Pitch> {

    public static final int NOTES_IN_OCTAVE = 12;
    public static final int MIN_MIDI_VALUE = 0;
    public static final int MAX_MIDI_VALUE = 127;
    public static final PitchRange MIDI_RANGE = new PitchRange(MIN_MIDI_VALUE, MAX_MIDI_VALUE);
    private static final IntFunction<String> ERROR_FORMAT = (midiValue) -> String.format("Failed to create pitch with " +
            "value: %d. Midi value should be in range <0,127>.", midiValue);
    private final NoteName noteName;
    private final Octave octave;
    private final Integer midiValue;
    private Pitch(NoteName noteName, Octave octave, Integer midiValue) {
        this.noteName = noteName;
        this.octave = octave;
        this.midiValue = midiValue;
    }

    public static Pitch createWithNames(NoteName noteName, Octave octave) throws IllegalArgumentException {
        int midiValue = midiFromNotes(noteName, octave);
        if (MIDI_RANGE.outOfRange(midiValue)) {
            throw new IllegalArgumentException(ERROR_FORMAT.apply(midiValue));
        } else {
            return new Pitch(noteName, octave, midiValue);
        }
    }

    public static Pitch createWithMidi(int midiValue) throws IllegalArgumentException {
        if (MIDI_RANGE.outOfRange(midiValue)) {
            throw new IllegalArgumentException(ERROR_FORMAT.apply(midiValue));
        }
        int pitchValue = midiValue % NOTES_IN_OCTAVE;
        //loss of fractional part
        int octaveNumber = midiValue / NOTES_IN_OCTAVE;
        return new Pitch(NoteName.values()[pitchValue], Octave.values()[octaveNumber], midiValue);
    }

    public static Pitch createWithUpInterval(Pitch base, PitchInterval interval) throws IllegalArgumentException {
        int semitonesToAdd = interval.semitones();
        return Pitch.createWithMidi(base.midiValue + semitonesToAdd);
    }

    public static Pitch createWithDownInterval(Pitch base, PitchInterval interval) throws IllegalArgumentException {
        int semitonesToAdd = interval.semitones();
        return Pitch.createWithMidi(base.midiValue - semitonesToAdd);
    }

    public static int midiFromNotes(NoteName noteName, Octave octave) {
        return noteName.value() + (NOTES_IN_OCTAVE * octave.number());
    }

    @Override
    public String toString() {
        return "Pitch{" +
                "noteName=" + noteName +
                ", octave=" + octave +
                ", midiValue=" + midiValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pitch pitch = (Pitch) o;

        if (noteName != pitch.noteName) return false;
        if (octave != pitch.octave) return false;
        return midiValue != null ? midiValue.equals(pitch.midiValue) : pitch.midiValue == null;

    }

    @Override
    public int hashCode() {
        int result = noteName != null ? noteName.hashCode() : 0;
        result = 31 * result + (octave != null ? octave.hashCode() : 0);
        result = 31 * result + (midiValue != null ? midiValue.hashCode() : 0);
        return result;
    }

    public Integer getMidiValue() {
        return midiValue;
    }

    public NoteName getNoteName() {
        return noteName;
    }

    public Octave getOctave() {
        return octave;
    }

    @Override
    public int compareTo(Pitch o) {
        return this.midiValue.compareTo(o.getMidiValue());
    }
}
