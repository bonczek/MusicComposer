package genetic.util;

import genetic.representation.Chromosome;
import jm.constants.Durations;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import music.Note;
import music.Rest;
import music.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Converter {

    public static double DEFAULT_RHYTHMIC_VALUE = Durations.SIXTEENTH_NOTE;

    static Predicate<Integer> REST_VALUE = value -> value.equals(-1);

    static Predicate<Integer> TENUTO_VALUE = value -> value.equals(-2);

    static Predicate<Integer> MIDI_VALUE = value -> value >= 0 && value <= 127;

    public static List<Note> fromChromosome(Chromosome chromosome) throws IllegalArgumentException {
        List<Note> noteList = new ArrayList<>();
        for (Integer value : chromosome.getGenesValues()) {
            if (MIDI_VALUE.test(value)) {
                noteList.add(new Sound(value, DEFAULT_RHYTHMIC_VALUE));
            } else if (REST_VALUE.test(value)) {
                noteList.add(new Rest(DEFAULT_RHYTHMIC_VALUE));
            } else if (TENUTO_VALUE.test(value)) {
                if (!noteList.isEmpty()) {
                    noteList.get(noteList.size() - 1).addRhytmValue(DEFAULT_RHYTHMIC_VALUE);
                } else {
                    noteList.add(new Rest(DEFAULT_RHYTHMIC_VALUE));
                }
            } else {
                throw new IllegalArgumentException(
                        String.format("Failed to parse gene value. Value: %d is not supported by this parser.", value));
            }
        }
        return noteList;
    }

    public static String humanReadable(Chromosome chromosome) {
        StringBuilder builder = new StringBuilder();
        for (Integer value : chromosome.getGenesValues()) {
            if (MIDI_VALUE.test(value)) {
                Sound sound = new Sound(value, DEFAULT_RHYTHMIC_VALUE);
                builder.append(String.format("%s%d", sound.getPitch().name(), sound.getOctave().number()));
            } else if (REST_VALUE.test(value)) {
                builder.append("-");
            } else if (TENUTO_VALUE.test(value)) {
                builder.append("%");
            } else {
                throw new IllegalArgumentException(
                        String.format("Failed to parse gene value. Value: %d is not supported by this parser.", value));
            }
            builder.append("|");
        }
        return builder.toString();
    }

    public static Score convertToJMusicScore(Chromosome chromosome) {
        List<Note> noteList = fromChromosome(chromosome);
        Phrase phrase = new Phrase();
        for (Note note : noteList) {
            if (note instanceof Rest) {
                jm.music.data.Note jMusicNote = new jm.music.data.Note(jm.music.data.Note.REST, note.getRhythmValue());
                phrase.add(jMusicNote);
            } else if (note instanceof Sound) {
                jm.music.data.Note jMusicNote = new jm.music.data.Note(((Sound) note).getMidiValue(),
                        note.getRhythmValue());
                phrase.add(jMusicNote);
            } else {
                break;
            }
        }
        Part part = new Part(phrase);
        return new Score(part);
    }

}
