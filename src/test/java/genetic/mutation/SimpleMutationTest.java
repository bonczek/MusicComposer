package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.when;

public class SimpleMutationTest {

    private Random randomMock = Mockito.mock(Random.class);
    private SimpleMutation mutation = new SimpleMutation(randomMock);

    @Test
    public void testMutateChromosome() throws Exception {
        List<Chromosome> chromosomes = prepareData();
        when(randomMock.nextInt(chromosomes.get(0).getSize())).thenReturn(2, 3);
        when(randomMock.nextInt(Constants.MAX_MIDI_VALUE.value() + 3)).thenReturn(9, 0);
        chromosomes.forEach(mutation::mutate);

        assertThat(chromosomes.get(0).getGenesValues(), contains(4, 4, 7, 2, 6, 1, 3, 5));
        assertThat(chromosomes.get(1).getGenesValues(), contains(9, 8, 8, -2, 6, 7, 8, 6));
    }

    private List<Chromosome> prepareData() {
        Chromosome firstChromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(4, 4, 0, 2, 6, 1, 3, 5));
        Chromosome secondChromosome = ChromosomeData.createWithIntegerValues(Arrays.asList(9, 8, 8, 10, 6, 7, 8, 6));
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(firstChromosome);
        chromosomes.add(secondChromosome);
        return chromosomes;
    }
}