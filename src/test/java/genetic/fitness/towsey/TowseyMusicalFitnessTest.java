package genetic.fitness.towsey;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.representation.Chromosome;
import org.testng.annotations.Test;

public class TowseyMusicalFitnessTest {

    @Test
    public void testRateChromosome() throws Exception {
        Integer[] geneValues = {23, -2, -2, 31, -1, -2, 79, 55, -1, -2, -2, -2, -2, 120, 63, 30, 22, 79, 14, 45, 46,
                75, 37, 62, 121};
        Chromosome testChromosome = Chromosome.createWithIntegerValues(Arrays.asList(geneValues));
        TowseyMusicalFitness calculator = new TowseyMusicalFitness();

        int a = calculator.rateChromosome(testChromosome);
        int b = a;

    }
}