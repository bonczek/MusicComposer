package music;

public enum PitchInterval {
    PERFECT_UNISON(0), MINOR_SECOND(1), MAJOR_SECOND(2), MINOR_THIRD(3), MAJOR_THIRD(4), PERFECT_FOURTH(5),
    TRITONE(6), PERFECT_FIFTH(7), MINOR_SIXTH(8), MAJOR_SIXTH(9), MINOR_SEVENTH(10), MAJOR_SEVENTH(11);

    private int semitones;

    PitchInterval(int semitones) {
        this.semitones = semitones;
    }

    public int semitones() {
        return semitones;
    }
}
