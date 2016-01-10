package genetic.initial;

import genetic.Chromosome;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.when;

public class RandomPopulationGeneratorTest {

    private final List<Integer> AVAILABLE_VALUES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private Random randomMock = Mockito.mock(Random.class);
    private RandomPopulationGenerator populationGenerator = new RandomPopulationGenerator(randomMock);

    @Test
    public void testGeneratePopulation() throws Exception {
        int populationSize = 5;
        int chromosomeLength = 4;

        when(randomMock.nextInt(AVAILABLE_VALUES.size())).thenReturn(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5);
        List<Chromosome> population = populationGenerator.generatePopulation(populationSize, chromosomeLength, AVAILABLE_VALUES);

        assertThat(population.size(), is(populationSize));
        assertThat(population.get(0).getSize(), is(chromosomeLength));
        assertThat(population.get(0).getGenesValues(), contains(2, 2, 2, 2));
        assertThat(population.get(1).getGenesValues(), contains(3, 3, 3, 3));
        assertThat(population.get(2).getGenesValues(), contains(4, 4, 4, 4));
        assertThat(population.get(3).getGenesValues(), contains(5, 5, 5, 5));
        assertThat(population.get(4).getGenesValues(), contains(6, 6, 6, 6));
    }
}