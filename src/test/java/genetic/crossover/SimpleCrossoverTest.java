package genetic.crossover;

import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.when;

public class SimpleCrossoverTest {

    private Random randomMock = Mockito.mock(Random.class);

    private SimpleCrossover mixCrossover = new SimpleCrossover(randomMock);

    @Test
    public void testCrossOverChromosomes() throws Exception {
        ChromosomePair chromosomePair = prepareData();
        when(randomMock.nextInt(chromosomePair.getFirst().getSize() - 2)).thenReturn(3);
        ChromosomePair afterCrossover = chromosomePair.crossover(mixCrossover);

        assertThat(afterCrossover.getFirst().getGenesValues(), contains(1, 2, 3, 4, 13, 14, 15, 16));
        assertThat(afterCrossover.getSecond().getGenesValues(), contains(9, 10, 11, 12, 5, 6, 7, 8));
    }

    private ChromosomePair prepareData() {
        Chromosome first = ChromosomeData.createWithIntegerValues(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Chromosome second = ChromosomeData.createWithIntegerValues(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16));
        return new ChromosomePair(first, second);
    }
}