package music.harmony;

import music.notes.pitch.NoteName;

public enum ChordText {
    C(ChordName.MAJOR, NoteName.C), Cis(ChordName.MAJOR, NoteName.C_SHARP), D(ChordName.MAJOR, NoteName.D),
    Dis(ChordName.MAJOR, NoteName.D_SHARP), E(ChordName.MAJOR, NoteName.E), F(ChordName.MAJOR, NoteName.F),
    Fis(ChordName.MAJOR, NoteName.F_SHARP), G(ChordName.MAJOR, NoteName.G), Gis(ChordName.MAJOR, NoteName.G_SHARP),
    A(ChordName.MAJOR, NoteName.A), B(ChordName.MAJOR, NoteName.A_SHARP), H(ChordName.MAJOR, NoteName.B),

    c(ChordName.MINOR, NoteName.C), cis(ChordName.MINOR, NoteName.C_SHARP), d(ChordName.MINOR, NoteName.D),
    dis(ChordName.MINOR, NoteName.D_SHARP), e(ChordName.MINOR, NoteName.E), f(ChordName.MINOR, NoteName.F),
    fis(ChordName.MINOR, NoteName.F_SHARP), g(ChordName.MINOR, NoteName.G), gis(ChordName.MINOR, NoteName.G_SHARP),
    a(ChordName.MINOR, NoteName.A), b(ChordName.MINOR, NoteName.A_SHARP), h(ChordName.MINOR, NoteName.B);


    private ChordName chordName;

    private NoteName baseNoteName;

    ChordText(ChordName chordName, NoteName baseNoteName) {
        this.chordName = chordName;
        this.baseNoteName = baseNoteName;
    }

    public Harmony getChordHarmony() {
        return new Harmony(chordName, baseNoteName);
    }
}
