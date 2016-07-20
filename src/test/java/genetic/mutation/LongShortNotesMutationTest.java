package genetic.mutation;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.representation.Chromosome;
import genetic.representation.Constants;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class LongShortNotesMutationTest {

    private Random randomMock = Mockito.mock(Random.class);

    private LongShortNotesMutation mutation = new LongShortNotesMutation(randomMock);

    @Test
    public void testMutate_givenLongNoteMutation() throws Exception {
        int notesNumber = 6;
        int numberOfNoteToMutate = 2;
        int chosenTenutoNumber = 3;
        int indexOfNewNote = 3 + chosenTenutoNumber;
        int numberOfTenutoGenes = 5;
        Integer[] testValues = {0, 30, 2, -2, -2, -2, -2, -2, 83, 127, -1};
        Chromosome chromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(testValues));

        when(randomMock.nextInt(notesNumber)).thenReturn(numberOfNoteToMutate);
        when(randomMock.nextInt(numberOfTenutoGenes)).thenReturn(chosenTenutoNumber);

        Chromosome mutated = mutation.mutate(chromosome);

        for (int i = 0; i < mutated.getSize(); i++) {
            if (i == indexOfNewNote) {
                assertThat(mutated.getGene(i).getValue(), is(new Integer(2).shortValue()));
            } else {
                assertThat(mutated.getGene(i).getValue(), is(testValues[i].shortValue()));
            }
        }
    }

    @Test
    public void testMutate_givenShortNoteMutation() throws Exception {
        int notesNumber = 9;
        int numberOfNoteToMutate = 3;
        int indexOfNewTenuto = 4;
        Integer[] testValues = {0, 30, 2, 8, 5, 12, -2, -2, 83, 127, -1};
        Chromosome chromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(testValues));

        when(randomMock.nextInt(notesNumber)).thenReturn(numberOfNoteToMutate);
        when(randomMock.nextInt(LongShortNotesMutation.SHORT_NOTES_EXTENDING_LIMIT)).thenReturn(0);

        Chromosome mutated = mutation.mutate(chromosome);
        for (int i = 0; i < mutated.getSize(); i++) {
            if (i == indexOfNewTenuto) {
                assertThat(mutated.getGene(i).getValue(), is((short) Constants.TENUTO.value()));
            } else {
                assertThat(mutated.getGene(i).getValue(), is(testValues[i].shortValue()));
            }
        }
    }

    //@todo test with short value at the end
}