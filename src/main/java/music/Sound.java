package music;

import genetic.representation.Constants;

public class Sound extends Note {

    private NoteName noteName;

    private Octave octave;

    private int midiValue;

    public Sound(NoteName noteName, Octave octave, double rhythmValue) {
        super(rhythmValue);
        this.noteName = noteName;
        this.octave = octave;
        this.midiValue = noteName.value() + (12 * octave.number());
    }

    public Sound(int midiValue, double rhythmValue) {
        super(rhythmValue);
        this.midiValue = midiValue;
        if (midiValue < 0 || midiValue > Constants.MAX_MIDI_VALUE.value()) {
            throw new IllegalArgumentException(String.format("Failed to create sound with given midi value: %s. Midi " +
                    "value should be in range <0,127>.", midiValue));
        }

        int pitchValue = midiValue % 12;
        //loss of fractional part
        int octaveNumber = midiValue / 12;
        this.noteName = NoteName.values()[pitchValue];
        this.octave = Octave.values()[octaveNumber];
        //@todo index out of bounds

    }

    public static Sound createSoundWithInterval(Sound baseSound, PitchInterval interval) {
        int semitonesToAdd = interval.semitones();
        return new Sound(baseSound.midiValue + semitonesToAdd, baseSound.rhythmValue);
    }

    public int getMidiValue() {
        return midiValue;
    }

    public NoteName getNoteName() {
        return noteName;
    }

    public void setNoteName(NoteName noteName) throws IllegalArgumentException {
        this.noteName = noteName;
        this.midiValue = noteName.value() + (12 * octave.number());
        if (midiValue < 0 || midiValue > Constants.MAX_MIDI_VALUE.value()) {
            throw new IllegalArgumentException(String.format("Failed set pitch value: %s. Midi " +
                    "value should be in range <0,127> but was %d.", noteName, midiValue));
        }
    }

    public Octave getOctave() {
        return octave;
    }

    public void setOctave(Octave octave) throws IllegalArgumentException {
        this.octave = octave;
        this.midiValue = noteName.value() + (12 * octave.number());
        if (midiValue < 0 || midiValue > Constants.MAX_MIDI_VALUE.value()) {
            throw new IllegalArgumentException(String.format("Failed set octave value: %s. Midi " +
                    "value should be in range <0,127> but was %d.", octave, midiValue));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sound sound = (Sound) o;

        if (Double.compare(sound.rhythmValue, rhythmValue) != 0) return false;
        if (noteName != sound.noteName) return false;
        return octave == sound.octave;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = noteName.hashCode();
        result = 31 * result + octave.hashCode();
        temp = Double.doubleToLongBits(rhythmValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
