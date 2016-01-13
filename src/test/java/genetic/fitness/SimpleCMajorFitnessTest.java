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
        List<Chromosome> population = preparePopulation();
        fitness.calculateFitness(population);

        assertThat(population.get(0).getFitness(), is(60));
        assertThat(population.get(1).getFitness(), is(30));
        assertThat(population.get(2).getFitness(), is(5));
    }

    private List<Chromosome> preparePopulation() {
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(Note.C_1.value(), Note.E_1.value(), Note.G_2.value())));
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(Note.D_1.value(), Note.F_1.value(), Note.A_2.value())));
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(Note.C_SHARP_1.value(), Note.REST.value(), Note.A_SHARP_2.value())));
        return chromosomes;
    }
}