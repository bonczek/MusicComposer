package genetic.util;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.representation.Chromosome;
import genetic.representation.Constants;
import jm.constants.Durations;
import jm.constants.Pitches;
import jm.music.data.Phrase;
import music.notes.Note;
import music.notes.Rest;
import music.notes.Sound;
import music.notes.pitch.NoteName;
import music.notes.pitch.Octave;
import music.notes.pitch.Pitch;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConverterTest {

    @Test
    public void testFromChromosome() throws Exception {
        Integer[] testValues = {0, 30, 2, -2, -2, -2, -1, -2, 83, 127, -1};
        Chromosome chromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(testValues));

        List<Note> noteList = Converter.fromChromosome(chromosome);

        assertThat(noteList.size(), is(7));
        assertThat(noteList.get(0).getRhythmValue(), is(Converter.DEFAULT_RHYTHMIC_VALUE));
        assertThat(noteList.get(1), instanceOf(Sound.class));

        Sound longNote = (Sound) noteList.get(2);
        assertThat(longNote.getRhythmValue(), is(Durations.QUARTER_NOTE));
        assertThat(longNote.getPitch().getOctave(), is(Octave.SUBSUBCONTRA));
        assertThat(longNote.getPitch().getNoteName(), is(NoteName.D));
        assertThat(noteList.get(3), instanceOf(Rest.class));
        assertThat(noteList.get(3).getRhythmValue(), is(Durations.EIGHTH_NOTE));
        assertThat(noteList.get(6), instanceOf(Rest.class));
        assertThat(noteList.get(6).getRhythmValue(), is(Durations.SIXTEENTH_NOTE));
    }

    @Test
    public void testFromChromosomeWithIndexes() throws Exception {
        Integer[] testValues = {0, 30, 2, -2, -2, -2, -1, -2, 83, 127, -1};
        Chromosome chromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(testValues));

        Map<Integer, Note> noteMap = Converter.fromChromosomeWithIndexes(chromosome);

        assertThat(noteMap.size(), is(7));
        assertThat(noteMap.get(0).getRhythmValue(), is(Converter.DEFAULT_RHYTHMIC_VALUE));
        assertThat(noteMap.get(1), instanceOf(Sound.class));

        Sound longNote = (Sound) noteMap.get(2);
        assertThat(longNote.getRhythmValue(), is(Durations.QUARTER_NOTE));
        assertThat(longNote.getPitch().getOctave(), is(Octave.SUBSUBCONTRA));
        assertThat(longNote.getPitch().getNoteName(), is(NoteName.D));
        assertThat(noteMap.get(6), instanceOf(Rest.class));
        assertThat(noteMap.get(6).getRhythmValue(), is(Durations.EIGHTH_NOTE));
        assertThat(noteMap.get(10), instanceOf(Rest.class));
        assertThat(noteMap.get(10).getRhythmValue(), is(Durations.SIXTEENTH_NOTE));
    }

    @Test
    public void testNotesToChromosomeConversion() throws Exception {
        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.A, Octave.CONTRA), Durations.SIXTEENTH_NOTE),
                new Rest(Durations.HALF_NOTE), new Sound(Pitch.createWithNames(NoteName.D, Octave.FOUR_LINED),
                Durations.QUARTER_NOTE), new Rest(Durations.DOTTED_EIGHTH_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        Chromosome chromosome = Converter.fromNotes(noteList, 16);
        assertThat(chromosome.getGene(0).getValue(), is((short) 33));
        assertThat(chromosome.getGene(1).getValue(), is((short) Constants.REST.value()));
        assertThat(chromosome.getGene(9).getValue(), is((short) 98));
        assertThat(chromosome.getGene(13).getValue(), is((short) Constants.REST.value()));
        for (int i = 2; i < 16; i++) {
            if (i == 9 || i == 13) {
                continue;
            }
            assertThat(chromosome.getGene(i).getValue(), is((short) Constants.TENUTO.value()));
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidNotesConversion() throws Exception {
        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.A, Octave.CONTRA), Durations.QUARTER_NOTE), new Rest
                (-2.0)};
        List<Note> noteList = Arrays.asList(notes);

        Converter.fromNotes(noteList, 12);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTooManyNotesConversion() throws Exception {
        Note[] notes = {new Sound(Pitch.createWithNames(NoteName.A, Octave.CONTRA), Durations.WHOLE_NOTE), new Rest
                (Durations.QUARTER_NOTE)};
        List<Note> noteList = Arrays.asList(notes);

        Converter.fromNotes(noteList, 16);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFromChromosome_givenNotSupportedValues() throws Exception {
        Integer[] testValues = {0, 30, 2, -2, -2, -20, -1, -2, 83, 127, -1};
        Chromosome chromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(testValues));

        Converter.fromChromosome(chromosome);
    }

    @Test
    public void testReadMidi_givenSimpleMelody() throws Exception {
        String midiTestFile = "simple_melody.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        Phrase phrase = Converter.readMidiMelodyLine(filePath.getPath());
        assertThat(phrase.size(), is(17));
        assertThat(phrase.getNote(0).getPitch(), is(Pitches.C5));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testReadMidi_givenEmptyMelody() throws Exception {
        String midiTestFile = "empty.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        Converter.readMidiMelodyLine(filePath.getPath());
    }

    @Test(expectedExceptions = IOException.class)
    public void testReadMidi_givenNotExistingFile() throws Exception {
        String midiTestFile = "qwe.mid";

        Converter.readMidiMelodyLine(midiTestFile);
    }

    @Test
    public void testConvertToMelodyLine_givenSimpleMelody() throws Exception {
        String midiTestFile = "simple_melody.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        List<Note> melodyLine = Converter.convertMidiToMelodyLine(filePath.getPath());
        assertThat(melodyLine.size(), is(17));
        assertThat(melodyLine.get(0).getRhythmValue(), is(Durations.QUARTER_NOTE));
        assertThat(((Sound) melodyLine.get(0)).getPitch(), is(Pitch.createWithNames(NoteName.C, Octave.TWO_LINED)));
        assertThat(((Sound) melodyLine.get(8)).getPitch(), is(Pitch.createWithNames(NoteName.C, Octave.THREE_LINED)));
        assertThat(melodyLine.get(12), instanceOf(Rest.class));
    }

    @Test
    public void testConvertToMelodyLine_givenRestAtBeginning() throws Exception {
        String midiTestFile = "yesterday.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        List<Note> melodyLine = Converter.convertMidiToMelodyLine(filePath.getPath());
        assertThat(melodyLine.get(0).getRhythmValue(), is(Durations.WHOLE_NOTE));
        assertThat(melodyLine.get(0), is(instanceOf(Rest.class)));
    }

    @Test
    public void testConvertToMelodyLine_givenRestAtTheEnd() throws Exception {
        String midiTestFile = "swan_lake.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        List<Note> melodyLine = Converter.convertMidiToMelodyLine(filePath.getPath());
        assertThat(melodyLine.get(82).getRhythmValue(), is(Durations.DOTTED_HALF_NOTE));
        assertThat(melodyLine.get(82), is(instanceOf(Rest.class)));
    }

    @Test
    public void printThesisExamples() throws Exception {
        int measureLength = 16;
        Note[] original = {
                new Sound(Pitch.createWithNames(NoteName.A, Octave.ONE_LINED), Durations.SIXTEENTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.TWO_LINED), Durations.SIXTEENTH_NOTE),
                new Rest(Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.D, Octave.ONE_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G, Octave.TWO_LINED), Durations.QUARTER_NOTE),
                new Sound(Pitch.createWithNames(NoteName.F, Octave.ONE_LINED), Durations.EIGHTH_NOTE),
                new Sound(Pitch.createWithNames(NoteName.G, Octave.ONE_LINED), Durations.EIGHTH_NOTE)
        };
        List<Note> originalList = Arrays.asList(original);
        Chromosome chromosome = Converter.fromNotes(originalList, measureLength);
        System.out.println(chromosome.toString());

        List<Note> intervals = new ArrayList<>(originalList);
        intervals.set(4, new Sound(Pitch.createWithNames(NoteName.A, Octave.ONE_LINED), Durations.QUARTER_NOTE));
        chromosome = Converter.fromNotes(intervals, measureLength);
        System.out.println(chromosome.toString());

        List<Note> transpose = new ArrayList<>(originalList);
        transpose.set(0, new Sound(Pitch.createWithNames(NoteName.F_SHARP, Octave.TWO_LINED), Durations
                .SIXTEENTH_NOTE));
        chromosome = Converter.fromNotes(transpose, measureLength);
        System.out.println(chromosome.toString());

        List<Note> length = new ArrayList<>(originalList);
        length.set(1, new Sound(Pitch.createWithNames(NoteName.D, Octave.TWO_LINED), Durations.EIGHTH_NOTE));
        length.set(2, new Rest(Durations.SIXTEENTH_NOTE));
        chromosome = Converter.fromNotes(length, measureLength);
        System.out.println(chromosome.toString());

        List<Note> rests = new ArrayList<>(originalList);
        rests.set(4, new Rest(Durations.QUARTER_NOTE));
        chromosome = Converter.fromNotes(rests, measureLength);
        System.out.println(chromosome.toString());

        List<Note> rhythmical = new ArrayList<>(originalList);
        rhythmical.remove(6);
        rhythmical.remove(5);
        rhythmical.remove(4);
        rhythmical.set(3, new Sound(Pitch.createWithNames(NoteName.D, Octave.ONE_LINED), Durations.DOTTED_HALF_NOTE));
        chromosome = Converter.fromNotes(rhythmical, measureLength);
        System.out.println(chromosome.toString());

        List<Note> shortLong = new ArrayList<>(originalList);
        shortLong.remove(2);
        shortLong.remove(1);
        shortLong.set(0, new Sound(Pitch.createWithNames(NoteName.A, Octave.ONE_LINED), Durations.QUARTER_NOTE));
        chromosome = Converter.fromNotes(shortLong, measureLength);
        System.out.println(chromosome.toString());
    }
}