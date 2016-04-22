package music;

import java.util.function.IntFunction;

/**
 * Pitch of the note restricted to midi range <0, 127>
 */
public class Pitch implements Comparable<Pitch> {

    public static final int NOTES_IN_OCTAVE = 12;

    private static final int MIN_MIDI_VALUE = 0;

    private static final int MAX_MIDI_VALUE = 127;
    private static final PitchRange MIDI_RANGE = new PitchRange(MIN_MIDI_VALUE, MAX_MIDI_VALUE);
    private static IntFunction<String> ERROR_FORMAT = (midiValue) -> String.format("Failed to create pitch with " +
            "value: %d. Midi value should be in range <0,127>.", midiValue);
    private NoteName noteName = NoteName.A;
    private Octave octave = Octave.CONTRA;
    private Integer midiValue = 1;

    private Pitch() {
    }

    public static Pitch createWithNames(NoteName noteName, Octave octave) throws IllegalArgumentException {
        Pitch pitch = new Pitch();
        pitch.noteName = noteName;
        pitch.octave = octave;
        pitch.midiValue = midiFromNotes(noteName, octave);
        if (MIDI_RANGE.outOfRange(pitch.midiValue)) {
            throw new IllegalArgumentException(ERROR_FORMAT.apply(pitch.midiValue));
        }
        return pitch;
    }

    public static Pitch createWithMidi(int midiValue) throws IllegalArgumentException {
        Pitch pitch = new Pitch();
        pitch.midiValue = midiValue;
        if (MIDI_RANGE.outOfRange(midiValue)) {
            throw new IllegalArgumentException(ERROR_FORMAT.apply(midiValue));
        }
        int pitchValue = midiValue % NOTES_IN_OCTAVE;
        //loss of fractional part
        int octaveNumber = midiValue / NOTES_IN_OCTAVE;
        pitch.noteName = NoteName.values()[pitchValue];
        pitch.octave = Octave.values()[octaveNumber];
        return pitch;
    }

    public static Pitch createWithInterval(Pitch base, PitchInterval interval) throws IllegalArgumentException {
        int semitonesToAdd = interval.semitones();
        return Pitch.createWithMidi(base.midiValue + semitonesToAdd);
    }

    public static int midiFromNotes(NoteName noteName, Octave octave) {
        return noteName.value() + (NOTES_IN_OCTAVE * octave.number());
    }

    public Integer getMidiValue() {
        return midiValue;
    }

    public NoteName getNoteName() {
        return noteName;
    }

    public void changeNoteName(NoteName noteName) throws IllegalArgumentException {
        int midiValue = midiFromNotes(noteName, octave);
        if (MIDI_RANGE.outOfRange(midiValue)) {
            throw new IllegalArgumentException(ERROR_FORMAT.apply(midiValue));
        }
        this.noteName = noteName;
        this.midiValue = midiValue;
    }

    public Octave getOctave() {
        return octave;
    }

    public void changeOctave(Octave octave) throws IllegalArgumentException {
        int midiValue = midiFromNotes(noteName, octave);
        if (MIDI_RANGE.outOfRange(midiValue)) {
            throw new IllegalArgumentException(ERROR_FORMAT.apply(midiValue));
        }
        this.octave = octave;
        this.midiValue = midiValue;
    }

    @Override
    public int compareTo(Pitch o) {
        return this.midiValue.compareTo(o.getMidiValue());
    }
}
