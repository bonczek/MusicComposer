package genetic.crossover;

import genetic.Chromosome;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class SimpleCrossoverTest {

    private SimpleCrossover mixCrossover = new SimpleCrossover(1.0);

    @Test
    public void testCrossOverChromosomes() throws Exception {
        List<Chromosome> chromosomes = prepareData();
        mixCrossover.crossOverChromosomes(chromosomes);

        assertThat(chromosomes.size(), is(2));
        assertThat(chromosomes.get(0).getGenesValues(), contains(1, 2, 3, 4, 13, 14, 15, 16));
        assertThat(chromosomes.get(1).getGenesValues(), contains(9, 10, 11, 12, 5, 6, 7, 8));

        mixCrossover.crossOver(chromosomes);
    }

    private List<Chromosome> prepareData() {
        Chromosome first = Chromosome.createWithIntegerValues(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Chromosome second = Chromosome.createWithIntegerValues(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16));
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(first);
        chromosomes.add(second);
        return chromosomes;
    }
}