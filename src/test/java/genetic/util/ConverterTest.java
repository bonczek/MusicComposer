package genetic.util;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.representation.Chromosome;
import genetic.representation.Constants;
import jm.constants.Durations;
import music.Note;
import music.NoteName;
import music.Octave;
import music.Pitch;
import music.Rest;
import music.Sound;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConverterTest {

    @Test
    public void testFromChromosome() throws Exception {
        Integer[] testValues = {0, 30, 2, -2, -2, -2, -1, -2, 83, 127, -1};
        Chromosome chromosome = Chromosome.createWithIntegerValues(Arrays.asList(testValues));

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
        Chromosome chromosome = Chromosome.createWithIntegerValues(Arrays.asList(testValues));

        Converter.fromChromosome(chromosome);
    }
}