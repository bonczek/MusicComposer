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

    private SimpleCrossover<Integer> mixCrossover = new SimpleCrossover<>(1.0);

    @Test
    public void testCrossOverChromosomes() throws Exception {
        List<Chromosome<Integer>> chromosomes = prepareData();
        mixCrossover.crossOverChromosomes(chromosomes);

        assertThat(chromosomes.size(), is(2));
        assertThat(chromosomes.get(0).getGenes(), contains(1, 2, 3, 4, 13, 14, 15, 16));
        assertThat(chromosomes.get(1).getGenes(), contains(9, 10, 11, 12, 5, 6, 7, 8));

        mixCrossover.crossOver(chromosomes);
    }

    private List<Chromosome<Integer>> prepareData() {
        Chromosome<Integer> first = new Chromosome<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Chromosome<Integer> second = new Chromosome<>(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16));
        List<Chromosome<Integer>> chromosomes = new ArrayList<>();
        chromosomes.add(first);
        chromosomes.add(second);
        return chromosomes;
    }
}