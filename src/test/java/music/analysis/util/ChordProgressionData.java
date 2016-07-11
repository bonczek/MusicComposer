package music.analysis.util;

import jm.constants.Durations;
import music.Chord;
import music.ChordName;
import music.ChordProgressionBuilder;
import music.Harmony;
import music.notes.pitch.NoteName;

import java.util.List;

public class ChordProgressionData {

    public static List<Chord> prepareTwoMeasuresChordProgressionWithLongDuration() {
        ChordProgressionBuilder progressionBuilder = new ChordProgressionBuilder();
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.C), Durations.WHOLE_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.F), Durations.WHOLE_NOTE);

        return progressionBuilder.getChordList();
    }

    public static List<Chord> prepareThreeMeasuresOfChordProgression() {
        ChordProgressionBuilder builder = new ChordProgressionBuilder();
        builder.appendChord(new Harmony(ChordName.MAJOR, NoteName.G), Durations.WHOLE_NOTE);
        builder.appendChord(new Harmony(ChordName.MAJOR, NoteName.C), Durations.HALF_NOTE);
        builder.appendChord(new Harmony(ChordName.MAJOR, NoteName.D), Durations.HALF_NOTE);
        builder.appendChord(new Harmony(ChordName.MINOR, NoteName.E), Durations.QUARTER_NOTE);
        builder.appendChord(new Harmony(ChordName.MINOR, NoteName.A), Durations.QUARTER_NOTE);
        builder.appendChord(new Harmony(ChordName.MAJOR, NoteName.C), Durations.QUARTER_NOTE);
        builder.appendChord(new Harmony(ChordName.MAJOR, NoteName.D), Durations.QUARTER_NOTE);
        return builder.getChordList();
    }

    public static List<Chord> prepareTwoMeasuresOfChordProgressionWithShortDurations() {
        ChordProgressionBuilder progressionBuilder = new ChordProgressionBuilder();
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.G), Durations.SIXTEENTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.C), Durations.SIXTEENTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.D), Durations.EIGHTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MINOR, NoteName.E), Durations.QUARTER_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MINOR, NoteName.A), Durations.QUARTER_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.C), Durations.QUARTER_NOTE);

        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.D), Durations.SIXTEENTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.D_SHARP), Durations.SIXTEENTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.E), Durations.SIXTEENTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.F), Durations.SIXTEENTH_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.F_SHARP), Durations.QUARTER_NOTE);
        progressionBuilder.appendChord(new Harmony(ChordName.MAJOR, NoteName.G), Durations.HALF_NOTE);

        return progressionBuilder.getChordList();
    }
}
