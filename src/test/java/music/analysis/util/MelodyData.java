package music.analysis.util;

import jm.constants.Durations;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;

import java.util.Arrays;
import java.util.List;

public class MelodyData {

    public static List<Note> prepareFourMeasureSample() {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.G, Octave.FOUR_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C, Octave.SUB_CONTRA), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.E, Octave.SUB_CONTRA), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.SUB_CONTRA), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A, Octave.SUB_CONTRA), Durations.EIGHTH_NOTE),
                new Rest(Durations.QUARTER_NOTE),

                new Sound(Pitch.createWithNames(NoteName.G, Octave.FOUR_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C, Octave.SUB_CONTRA), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),

                new Sound(Pitch.createWithNames(NoteName.F_SHARP, Octave.GREAT), Durations.WHOLE_NOTE),

                new Sound(Pitch.createWithNames(NoteName.F, Octave.THREE_LINED), Durations.WHOLE_NOTE)
        };
        return Arrays.asList(notes);
    }

    public static List<Note> prepareOneMeasureCMaj7Chord() {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.C, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.E, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.B, Octave.THREE_LINED), Durations.QUARTER_NOTE),
        };
        return Arrays.asList(notes);
    }

    public static List<Note> prepareTwoMeasuresWithShortNotes() {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.C, Octave.THREE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C_SHARP, Octave.THREE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D_SHARP, Octave.THREE_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),

                new Sound(Pitch.createWithNames(NoteName.G, Octave.THREE_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A, Octave.THREE_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C, Octave.THREE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A_SHARP, Octave.THREE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.B, Octave.THREE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C_SHARP, Octave.THREE_LINED), Durations.SIXTEENTH_NOTE),
                new Rest(Durations.HALF_NOTE)
        };
        return Arrays.asList(notes);
    }

    public static List<Note> prepareTwoMeasuresWithLongNotes() {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.C, Octave.THREE_LINED), Durations.WHOLE_NOTE),
                new Sound(Pitch.createWithNames(NoteName.C_SHARP, Octave.THREE_LINED), Durations.WHOLE_NOTE)
        };
        return Arrays.asList(notes);
    }


    public static List<Note> prepareThreeMeasuresOfMelody() {
        Note[] notes = {
                new Sound(Pitch.createWithNames(NoteName.C, Octave.THREE_LINED), Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.THREE_LINED), Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.B, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Rest(Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G, Octave.THREE_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.A, Octave.THREE_LINED), Durations.HALF_NOTE),
                new Sound(Pitch.createWithNames(NoteName.B, Octave.THREE_LINED), Durations.QUARTER_NOTE),
        };
        return Arrays.asList(notes);
    }
}
