package music.notes.pitch;

public enum Octave {
    SUBSUBCONTRA(0), SUB_CONTRA(1), CONTRA(2), GREAT(3), SMALL(4), ONE_LINED(5), TWO_LINED(6), THREE_LINED(7),
    FOUR_LINED(8), FIFE_LINED(9), SIX_LINED(10);

    private int number;

    Octave(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }
}
