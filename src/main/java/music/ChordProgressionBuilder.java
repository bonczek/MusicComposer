package music;

import java.util.ArrayList;
import java.util.List;

public class ChordProgressionBuilder {

    private List<Chord> chordList = new ArrayList<>();

    private double melodyTime = 0.0;

    public void appendChord(Harmony chordHarmony, double durationTime) {
        this.chordList.add(new Chord(chordHarmony, melodyTime, durationTime));
        melodyTime += durationTime;
    }

    public List<Chord> getChordList() {
        return chordList;
    }
}
