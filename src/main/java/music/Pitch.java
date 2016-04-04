package music;

public enum Pitch {
    C(0), C_SHARP(1), D(2), D_SHARP(3), E(4), F(5), F_SHARP(6), G(7), G_SHARP(8), A(9), A_SHARP(10), B(11);

    private int value;

    Pitch(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
