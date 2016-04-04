package music;

public class Sound extends Note {

    private Pitch pitch;

    private Octave octave;

    private int midiValue;

    public Sound(Pitch pitch, Octave octave, double rhythmValue) {
        super(rhythmValue);
        this.pitch = pitch;
        this.octave = octave;
        this.midiValue = pitch.value() * octave.number();
    }

    public Sound(int midiValue, double rhythmValue) {
        super(rhythmValue);
        this.midiValue = midiValue;

        int pitchValue = midiValue % 11;
        //loss of fractional part
        int octaveNumber = midiValue / 11;
        this.pitch = Pitch.values()[pitchValue];
        this.octave = Octave.values()[octaveNumber];
        //@todo index out of bounds

    }

    public int getMidiValue() {
        return midiValue;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Octave getOctave() {
        return octave;
    }

    public void setOctave(Octave octave) {
        this.octave = octave;
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
}
