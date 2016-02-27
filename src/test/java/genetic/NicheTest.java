package genetic;

import genetic.representation.Chromosome;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NicheTest {

    private Niche niche = new Niche();

    @Test
    public void testDecreaseFitnessValueForSimilar() throws Exception {
        List<Chromosome> data = prepareData();
        niche.decreaseFitnessValueForSimilar(data);

        int a = 2;

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