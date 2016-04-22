package genetic.fitness.towsey;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.fitness.FitnessFunction;
import genetic.representation.Chromosome;
import music.Harmony;
import music.NoteName;
import music.Scale;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TowseyMusicalFitnessTest {

    @Test
    public void testRateChromosome() throws Exception {
        Integer[] geneValues = {23, -2, -2, 31, -1, -2, 79, 55, -1, -2, -2, -2, -2, 120, 63, 30, 22, 79, 14, 45, 46,
                75, 37, 62, 121, 17, -2, -2, -1, -2, -2, -2};
        Chromosome[] testChromosome = {Chromosome.createWithIntegerValues(Arrays.asList(geneValues))};
        FitnessFunction calculator = new TowseyMusicalFitness(new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C), 2);

        calculator.calculateFitness(Arrays.asList(testChromosome));
        assertThat(testChromosome[0].getFitness(), is(1579));
    }
}