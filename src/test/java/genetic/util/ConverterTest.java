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
import java.util.List;

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
}