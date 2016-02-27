package genetic.representation;

/**
 * Created by adam on 10.01.16.
 */
public enum Note {

    C_1(24), C_SHARP_1(25), D_1(26), D_SHARP_1(27), E_1(28), F_1(29), F_SHARP_1(30), G_1(31), G_SHARP_1(32), A_1(33), A_SHARP_1(34), B_1(35),
    C_2(36), C_SHARP_2(37), D_2(38), D_SHARP_2(39), E_2(40), F_2(41), F_SHARP_2(42), G_2(43), G_SHARP_2(44), A_2(45), A_SHARP_2(46), B_2(47),
    REST(-1);//, TENUTO(-2);

    private int value;

    private Note(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
