package genetic.util;

import genetic.representation.Chromosome;
import jm.constants.Durations;
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

}
