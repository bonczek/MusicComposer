package genetic.util;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.representation.Chromosome;
import jm.constants.Durations;
import music.Note;
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
        assertThat(longNote.getOctave(), is(Octave.SUBSUBCONTRA));
        assertThat(longNote.getPitch(), is(Pitch.D));
        assertThat(noteList.get(3), instanceOf(Rest.class));
        assertThat(noteList.get(3).getRhythmValue(), is(Durations.EIGHTH_NOTE));
        assertThat(noteList.get(6), instanceOf(Rest.class));
        assertThat(noteList.get(6).getRhythmValue(), is(Durations.SIXTEENTH_NOTE));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFromChromosome_givenNotSupportedValues() throws Exception {
        Integer[] testValues = {0, 30, 2, -2, -2, -20, -1, -2, 83, 127, -1};
        Chromosome chromosome = Chromosome.createWithIntegerValues(Arrays.asList(testValues));

        Converter.fromChromosome(chromosome);
    }
}