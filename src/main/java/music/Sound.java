package music;



public class Sound extends Note {

    private Pitch pitch;

    public Sound(Pitch pitch, double rhythmValue) {
        super(rhythmValue);
        this.pitch = pitch;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sound sound = (Sound) o;

        if (Double.compare(sound.rhythmValue, rhythmValue) != 0) return false;
        return this.pitch.equals(sound.pitch);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = pitch != null ? pitch.hashCode() : 0;
        temp = Double.doubleToLongBits(rhythmValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
