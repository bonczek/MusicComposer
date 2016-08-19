package music.harmony.chords;

import music.harmony.Harmony;
import music.notes.pitch.NoteName;

public enum ChordText {
    C(ChordType.MAJOR, NoteName.C), Cis(ChordType.MAJOR, NoteName.C_SHARP), D(ChordType.MAJOR, NoteName.D),
    Dis(ChordType.MAJOR, NoteName.D_SHARP), E(ChordType.MAJOR, NoteName.E), F(ChordType.MAJOR, NoteName.F),
    Fis(ChordType.MAJOR, NoteName.F_SHARP), G(ChordType.MAJOR, NoteName.G), Gis(ChordType.MAJOR, NoteName.G_SHARP),
    A(ChordType.MAJOR, NoteName.A), B(ChordType.MAJOR, NoteName.A_SHARP), H(ChordType.MAJOR, NoteName.B),

    c(ChordType.MINOR, NoteName.C), cis(ChordType.MINOR, NoteName.C_SHARP), d(ChordType.MINOR, NoteName.D),
    dis(ChordType.MINOR, NoteName.D_SHARP), e(ChordType.MINOR, NoteName.E), f(ChordType.MINOR, NoteName.F),
    fis(ChordType.MINOR, NoteName.F_SHARP), g(ChordType.MINOR, NoteName.G), gis(ChordType.MINOR, NoteName.G_SHARP),
    a(ChordType.MINOR, NoteName.A), b(ChordType.MINOR, NoteName.A_SHARP), h(ChordType.MINOR, NoteName.B);


    private ChordType chordType;

    private NoteName baseNoteName;

    ChordText(ChordType chordType, NoteName baseNoteName) {
        this.chordType = chordType;
        this.baseNoteName = baseNoteName;
    }

    public Harmony getChordHarmony() {
        return new Harmony(chordType, baseNoteName);
    }
}
