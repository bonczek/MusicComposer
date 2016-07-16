package genetic.selection;

import genetic.fitness.type.SimpleReward;
import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class BinaryTournamentSelectionTest {

    private Random numberGenerator = Mockito.mock(Random.class);

    private BinaryTournamentSelection selection = new BinaryTournamentSelection(numberGenerator);

    @Test
    public void testSelectChromosomes() throws Exception {
        List<Chromosome> population = prepareChromosomes();

        when(numberGenerator.nextInt(population.size())).thenReturn(0, 1, 2, 0);
        ChromosomePair selected = selection.selectChromosomes(population);

        assertThat(selected.getFirst(), is(population.get(0)));
        assertThat(selected.getSecond(), is(population.get(2)));
    }

    private List<Chromosome> prepareChromosomes() {
        List<Chromosome> population = new ArrayList<>();
        Chromosome first = ChromosomeData.createWithIntegerValues(Arrays.asList(1, 2, 3));
        SimpleReward firstFitness = new SimpleReward();
        firstFitness.addReward(30);
        first.setFitness(firstFitness);
        population.add(first);
        Chromosome second = ChromosomeData.createWithIntegerValues(Arrays.asList(4, 5, 6));
        SimpleReward secondFitness = new SimpleReward();
        secondFitness.addReward(20);
        second.setFitness(secondFitness);
        population.add(second);
        Chromosome third = ChromosomeData.createWithIntegerValues(Arrays.asList(7, 8, 9));
        SimpleReward thirdFitness = new SimpleReward();
        thirdFitness.addReward(50);
        third.setFitness(thirdFitness);
        population.add(third);

        return population;
    }
}