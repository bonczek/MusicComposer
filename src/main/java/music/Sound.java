package music;

import genetic.representation.Constants;

public class Sound extends Note {

    private Pitch pitch;

    private Octave octave;

    private int midiValue;

    public Sound(Pitch pitch, Octave octave, double rhythmValue) {
        super(rhythmValue);
        this.pitch = pitch;
        this.octave = octave;
        this.midiValue = pitch.value() + (12 * octave.number());
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
        this.pitch = Pitch.values()[pitchValue];
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

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) throws IllegalArgumentException {
        this.pitch = pitch;
        this.midiValue = pitch.value() + (12 * octave.number());
        if (midiValue < 0 || midiValue > Constants.MAX_MIDI_VALUE.value()) {
            throw new IllegalArgumentException(String.format("Failed set pitch value: %s. Midi " +
                    "value should be in range <0,127> but was %d.", pitch, midiValue));
        }
    }

    public Octave getOctave() {
        return octave;
    }

    public void setOctave(Octave octave) throws IllegalArgumentException {
        this.octave = octave;
        this.midiValue = pitch.value() + (12 * octave.number());
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
        if (pitch != sound.pitch) return false;
        return octave == sound.octave;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = pitch.hashCode();
        result = 31 * result + octave.hashCode();
        temp = Double.doubleToLongBits(rhythmValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Interval interval(Sound nextNote) {
        return new Interval(nextNote.midiValue - midiValue);
    }
}
