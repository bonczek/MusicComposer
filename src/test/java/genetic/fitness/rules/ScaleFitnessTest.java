package genetic.fitness.rules;

import genetic.fitness.MusicalFitnessFunction;
import genetic.representation.Chromosome;
import music.Harmony;
import music.NoteName;
import music.Scale;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScaleFitnessTest {

    @Test
    public void testRateMelody_givenCMajorFitness() throws Exception {
        List<Chromosome> population = preparePopulation();
        Harmony cMajorScale = new Harmony(Scale.MAJOR_SCALE.intervals(), NoteName.C);
        MusicalFitnessFunction fitnessFunction = new ScaleFitness(cMajorScale, 10);

        fitnessFunction.calculateFitness(population);
        assertThat(population.get(0).getFitness(), is(30));
        assertThat(population.get(1).getFitness(), is(30));
        assertThat(population.get(2).getFitness(), is(0));
    }

    @Test
    public void testRateMelody_givenEMinorPentatonicFitness() throws Exception {
        List<Chromosome> population = preparePopulation();
        Harmony eMinorPentatonicScale = new Harmony(Scale.MINOR_PENTATONIC_SCALE.intervals(), NoteName.E);
        MusicalFitnessFunction fitnessFunction = new ScaleFitness(eMinorPentatonicScale, 10);

        fitnessFunction.calculateFitness(population);
        assertThat(population.get(0).getFitness(), is(20));
        assertThat(population.get(1).getFitness(), is(20));
        assertThat(population.get(2).getFitness(), is(0));
    }


    private List<Chromosome> preparePopulation() {
        List<Chromosome> chromosomes = new ArrayList<>();
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(NoteName.C.value(), NoteName.E.value(), NoteName.G
                .value())));
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(NoteName.D.value(), NoteName.F.value(), NoteName.A.value())));
        chromosomes.add(Chromosome.createWithIntegerValues(Arrays.asList(NoteName.C_SHARP.value(), -1, NoteName.A_SHARP
                .value())));
        return chromosomes;
    }
}