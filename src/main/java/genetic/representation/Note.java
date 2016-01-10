package genetic.representation;

/**
 * Created by adam on 10.01.16.
 */
public enum Note {

    C_0(12), C_SHARP_0(13), D_0(14), D_SHARP_0(15), E_0(16), F_0(17), F_SHARP_0(18), G_0(19), G_SHARP_0(20), A_0(21), A_SHARP_0(22), B_0(23),
    C_1(24), C_SHARP_1(25), D_1(26), D_SHARP_1(27), E_1(28), F_1(29), F_SHARP_1(30), G_1(31), G_SHARP_1(32), A_1(33), A_SHARP_1(34), B_1(35),
    REST(-1), TENUTO(-2);

    private int value;

    private Note(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
