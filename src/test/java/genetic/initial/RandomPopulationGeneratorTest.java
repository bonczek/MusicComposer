package genetic.initial;

import genetic.representation.Chromosome;
import genetic.representation.Constants;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.when;

public class RandomPopulationGeneratorTest {

    private static final int POPULATION_SIZE = 5;
    private static final int NUMBERS_OF_MEASURES = 1;

    private Random randomMock = Mockito.mock(Random.class);
    private RandomPopulationGenerator populationGenerator = new RandomPopulationGenerator(POPULATION_SIZE, NUMBERS_OF_MEASURES,
            randomMock);

    @Test
    public void testGeneratePopulation() throws Exception {

        when(randomMock.nextInt(Constants.MAX_MIDI_VALUE.value() + 3)).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2,
                3, 4, 0);
        List<Chromosome> population = populationGenerator.generatePopulation();

        assertThat(population.size(), is(POPULATION_SIZE));
        assertThat(population.get(0).getSize(), is(NUMBERS_OF_MEASURES * InitialPopulationGenerator.NOTES_IN_MEASURE));
        assertThat(population.get(0).getGenesValues(), contains(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, -2, -1, 0, 1, 2, -2));
        assertThat(population.get(1).getGenesValues(), contains(-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
                -2, -2));
        assertThat(population.get(2).getGenesValues(), contains(-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
                -2, -2));
        assertThat(population.get(3).getGenesValues(), contains(-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
                -2, -2));
        assertThat(population.get(4).getGenesValues(), contains(-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
                -2, -2));
    }
}