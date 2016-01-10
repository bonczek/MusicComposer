package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.representation.Note;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleCMajorFitnessTest {

    private SimpleCMajorFitness fitness = new SimpleCMajorFitness();

    @Test
    public void testCalculateFitness() throws Exception {
        List<Integer> results = fitness.calculateFitness(preparePopulation());

        assertThat(results.size(), is(3));
        assertThat(results.get(0), is(30));
        assertThat(results.get(1), is(15));
        assertThat(results.get(2), is(-18));
    }

    private List<Chromosome> preparePopulation() {
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(Note.C_0.value(), Note.E_0.value(), Note.G_1.value())));
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(Note.D_0.value(), Note.F_0.value(), Note.A_1.value())));
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(Note.C_SHARP_0.value(), Note.REST.value(), Note.A_SHARP_1.value())));
        return chromosomes;
    }
}