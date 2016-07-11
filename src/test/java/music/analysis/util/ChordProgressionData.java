package music.analysis.util;

import jm.constants.Durations;
import music.Chord;
import music.ChordName;
import music.Harmony;
import music.notes.pitch.NoteName;

import java.util.ArrayList;
import java.util.List;

public class ChordProgressionData {

    public static List<Chord> prepareTwoMeasuresChordProgressionWithLongDuration() {
        List<Chord> chordList = new ArrayList<>();
        double time = 0.0;
        Chord firstChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.C), time, Durations.WHOLE_NOTE);
        time += Durations.WHOLE_NOTE;
        chordList.add(firstChord);
        Chord secondChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.F), time, Durations.WHOLE_NOTE);
        chordList.add(secondChord);
        return chordList;
    }

    public static List<Chord> prepareThreeMeasuresOfChordProgression() {
        List<Chord> chordList = new ArrayList<>();
        double time = 0.0;
        Chord firstChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.G), time, Durations.WHOLE_NOTE);
        time += Durations.WHOLE_NOTE;
        chordList.add(firstChord);
        Chord secondChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.C), time, Durations.HALF_NOTE);
        time += Durations.HALF_NOTE;
        chordList.add(secondChord);
        Chord thirdChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.D), time, Durations.HALF_NOTE);
        time += Durations.HALF_NOTE;
        chordList.add(thirdChord);
        Chord fourthChord = new Chord(new Harmony(ChordName.MINOR, NoteName.E), time, Durations.QUARTER_NOTE);
        time += Durations.QUARTER_NOTE;
        chordList.add(fourthChord);
        Chord fifthChord = new Chord(new Harmony(ChordName.MINOR, NoteName.A), time, Durations.QUARTER_NOTE);
        time += Durations.QUARTER_NOTE;
        chordList.add(fifthChord);
        Chord sixthChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.C), time, Durations.QUARTER_NOTE);
        time += Durations.QUARTER_NOTE;
        chordList.add(sixthChord);
        Chord seventhChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.D), time, Durations.QUARTER_NOTE);
        chordList.add(seventhChord);
        return chordList;
    }

    public static List<Chord> prepareTwoMeasuresOfChordProgressionWithShortDurations() {
        List<Chord> chordList = new ArrayList<>();
        double time = 0.0;
        Chord firstChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.G), time, Durations.SIXTEENTH_NOTE);
        time += Durations.SIXTEENTH_NOTE;
        chordList.add(firstChord);
        Chord secondChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.C), time, Durations.SIXTEENTH_NOTE);
        time += Durations.SIXTEENTH_NOTE;
        chordList.add(secondChord);
        Chord thirdChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.D), time, Durations.EIGHTH_NOTE);
        time += Durations.EIGHTH_NOTE;
        chordList.add(thirdChord);
        Chord fourthChord = new Chord(new Harmony(ChordName.MINOR, NoteName.E), time, Durations.QUARTER_NOTE);
        time += Durations.QUARTER_NOTE;
        chordList.add(fourthChord);
        Chord fifthChord = new Chord(new Harmony(ChordName.MINOR, NoteName.A), time, Durations.QUARTER_NOTE);
        time += Durations.QUARTER_NOTE;
        chordList.add(fifthChord);
        Chord sixthChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.C), time, Durations.QUARTER_NOTE);
        time += Durations.QUARTER_NOTE;
        chordList.add(sixthChord);

        Chord seventhChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.D), time, Durations.SIXTEENTH_NOTE);
        time += Durations.SIXTEENTH_NOTE;
        chordList.add(seventhChord);
        Chord eightChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.D_SHARP), time, Durations.SIXTEENTH_NOTE);
        time += Durations.SIXTEENTH_NOTE;
        chordList.add(eightChord);
        Chord ninthChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.E), time, Durations.SIXTEENTH_NOTE);
        chordList.add(ninthChord);
        time += Durations.SIXTEENTH_NOTE;
        Chord tenthChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.F), time, Durations.SIXTEENTH_NOTE);
        chordList.add(tenthChord);
        time += Durations.SIXTEENTH_NOTE;
        Chord eleventhChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.F_SHARP), time, Durations.QUARTER_NOTE);
        chordList.add(eleventhChord);
        time += Durations.QUARTER_NOTE;
        Chord twelfthChord = new Chord(new Harmony(ChordName.MAJOR, NoteName.G), time, Durations.HALF_NOTE);
        chordList.add(twelfthChord);

        return chordList;
    }
}
