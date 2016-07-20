package genetic.util;

import genetic.representation.Chromosome;
import genetic.representation.Constants;
import genetic.representation.Gene;
import jm.constants.Durations;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Read;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
                noteList.add(new Sound(Pitch.createWithMidi(value), DEFAULT_RHYTHMIC_VALUE));
            } else if (REST_VALUE.test(value)) {
                noteList.add(new Rest(DEFAULT_RHYTHMIC_VALUE));
            } else if (TENUTO_VALUE.test(value)) {
                if (!noteList.isEmpty()) {
                    noteList.get(noteList.size() - 1).addRhythmValue(DEFAULT_RHYTHMIC_VALUE);
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

    public static Map<Integer, Note> fromChromosomeWithIndexes(Chromosome chromosome) throws IllegalArgumentException {
        Map<Integer, Note> noteIndexMap = new TreeMap<>();
        int lastNoteIndex = 0;
        for (int i = 0; i < chromosome.getSize(); i++) {
            int value = (int) chromosome.getGene(i).getValue();
            if (MIDI_VALUE.test(value)) {
                noteIndexMap.put(i, new Sound(Pitch.createWithMidi(value), DEFAULT_RHYTHMIC_VALUE));
                lastNoteIndex = i;
            } else if (REST_VALUE.test(value)) {
                noteIndexMap.put(i, new Rest(DEFAULT_RHYTHMIC_VALUE));
                lastNoteIndex = i;
            } else if (TENUTO_VALUE.test(value)) {
                if (!noteIndexMap.isEmpty()) {
                    noteIndexMap.get(lastNoteIndex).addRhythmValue(DEFAULT_RHYTHMIC_VALUE);
                } else {
                    noteIndexMap.put(i, new Rest(DEFAULT_RHYTHMIC_VALUE));
                    lastNoteIndex = i;
                }
            } else {
                throw new IllegalArgumentException(
                        String.format("Failed to parse gene value. Value: %d is not supported by this parser.", value));
            }
        }
        return noteIndexMap;
    }

    public static Chromosome fromNotes(List<Note> noteList, int chromosomeLength) throws IllegalArgumentException {
        List<Gene> genes = new ArrayList<>();
        for (Note note : noteList) {
            if (Double.compare(note.getRhythmValue(), 0.0) <= 0) {
                throw new IllegalArgumentException(String.format("Failed to convert given note. It has invalid rhythmic " +
                        "value: %f", note.getRhythmValue()));
            }
            if (note instanceof Sound) {
                Sound sound = (Sound) note;
                genes.add(new Gene(sound.getPitch().getMidiValue()));
            } else if (note instanceof Rest) {
                genes.add(new Gene(Constants.REST.value()));
            }
            double tenutoRhytm = note.getRhythmValue() - DEFAULT_RHYTHMIC_VALUE;
            while (Double.compare(tenutoRhytm, 0.0) > 0) {
                genes.add(new Gene(Constants.TENUTO.value()));
                tenutoRhytm = tenutoRhytm - DEFAULT_RHYTHMIC_VALUE;
            }
        }
        if (genes.size() != chromosomeLength) {
            throw new IllegalArgumentException(String.format("Failed to convert given notes to chromosome values. " +
                    "Notes have invalid rhythmic values and size of chromosome is %d", genes.size()));
        }
        return new Chromosome(genes);
    }

    public static String humanReadable(Chromosome chromosome) {
        StringBuilder builder = new StringBuilder();
        for (Integer value : chromosome.getGenesValues()) {
            if (MIDI_VALUE.test(value)) {
                Sound sound = new Sound(Pitch.createWithMidi(value), DEFAULT_RHYTHMIC_VALUE);
                builder.append(String.format("%s%d", sound.getPitch().getNoteName().name(), sound.getPitch().getOctave().number
                        ()));
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
                jm.music.data.Note jMusicNote = new jm.music.data.Note(((Sound) note).getPitch().getMidiValue(),
                        note.getRhythmValue());
                phrase.add(jMusicNote);
            } else {
                break;
            }
        }
        Part part = new Part(phrase);
        return new Score(part);
    }

    public static Phrase readMidiMelodyLine(String midiFilePath) throws IOException, IllegalArgumentException {
        if (Files.notExists(Paths.get(midiFilePath))) {
            throw new IOException(String.format("Failed to read %s midi file. File doesn't exist", midiFilePath));
        }
        Score score = new Score();
        Read.midi(score, midiFilePath);

        System.out.println(String.format("Score: %s has %d parts", midiFilePath, score.size()));
        Part part = score.getPart(0);
        if (part == null) {
            throw new IllegalArgumentException("Failed to read part in midi file %s. There is no part in read score.");
        }
        System.out.println(String.format("First part has: %d phrases", part.size()));
        Phrase phrase = part.getPhrase(0);
        if (phrase == null) {
            throw new IllegalArgumentException(String.format(
                    "Failed to read phrase in midi file %s. There is no phrase in chosen part.", midiFilePath));
        }
        System.out.println(String.format("Chosen phrase has: %d notes", phrase.size()));

        return phrase;
    }

    public static List<Note> convertMidiToMelodyLine(String filePath) throws IOException, IllegalArgumentException {
        List<Note> noteList = new ArrayList<>();

        Phrase phrase = readMidiMelodyLine(filePath);
        for (jm.music.data.Note note : phrase.getNoteArray()) {
            if (note.isRest()) {
                noteList.add(new Rest(note.getRhythmValue()));
            } else {
                noteList.add(new Sound(Pitch.createWithMidi(note.getPitch()), note.getRhythmValue()));
            }
        }

        return noteList;
    }

}
