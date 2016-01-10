package genetic.mutation;

import genetic.representation.Chromosome;
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

    private final List<Integer> values = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private Random randomMock = Mockito.mock(Random.class);
    private SimpleMutation mutation = new SimpleMutation(1.0, randomMock, values);

    @Test
    public void testMutateChromosome() throws Exception {
        List<Chromosome> chromosomes = prepareData();
        when(randomMock.nextInt(chromosomes.get(0).getSize())).thenReturn(2, 3);
        when(randomMock.nextInt(values.size())).thenReturn(9, 3);
        mutation.mutate(chromosomes);

        assertThat(chromosomes.get(0).getGenesValues(), contains(4, 4, 9, 2, 6, 1, 3, 5));
        assertThat(chromosomes.get(1).getGenesValues(), contains(9, 8, 8, 3, 6, 7, 8, 6));
    }

    private List<Chromosome> prepareData() {
        Chromosome firstChromosome = Chromosome.createWithIntegerValues(Arrays.asList(4, 4, 0, 2, 6, 1, 3, 5));
        Chromosome secondChromosome = Chromosome.createWithIntegerValues(Arrays.asList(9, 8, 8, 10, 6, 7, 8, 6));
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(firstChromosome);
        chromosomes.add(secondChromosome);
        return chromosomes;
    }
}