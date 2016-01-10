package genetic.selection;

import genetic.Chromosome;
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
        List<Integer> fitnessValues = Arrays.asList(30, 20, 50);

        when(numberGenerator.nextInt(population.size())).thenReturn(0, 1, 2, 0);
        List<Chromosome> selected = selection.selectChromosomes(population, fitnessValues);

        assertThat(selected.size(), is(2));
        assertThat(selected.get(0), is(population.get(0)));
        assertThat(selected.get(1), is(population.get(2)));
    }

    private List<Chromosome> prepareChromosomes() {
        List<Chromosome> population = new ArrayList<>();
        Chromosome first = Chromosome.createWithIntegerValues(Arrays.asList(1, 2, 3));
        population.add(first);
        Chromosome second = Chromosome.createWithIntegerValues(Arrays.asList(4, 5, 6));
        population.add(second);
        Chromosome third = Chromosome.createWithIntegerValues(Arrays.asList(7, 8, 9));
        population.add(third);

        return population;
    }
}