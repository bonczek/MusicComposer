package genetic.crossover;

import genetic.representation.ChromosomePair;
import genetic.representation.Gene;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class TwoPointMeasuresCrossoverTest {

    private Random randomMock = Mockito.mock(Random.class);

    private TwoPointMeasuresCrossover twoPointCrossover = new TwoPointMeasuresCrossover(randomMock);

    @Test
    public void testCrossover() throws Exception {
        ChromosomePair chromosomePair = ChromosomeData.prepareFourMeasuresPairOfChromosomes();
        int chromosomeLength = chromosomePair.getFirst().getSize();
        int availableCrossoverPoints = 2;
        int crossoverPoint = 0;
        when(randomMock.nextInt(availableCrossoverPoints)).thenReturn(crossoverPoint);

        twoPointCrossover.crossover(chromosomePair);
        Gene a = new Gene(1);
        Gene b = new Gene(1);
        boolean test = a.equals(b);

        ChromosomePair beforeCrossover = ChromosomeData.prepareFourMeasuresPairOfChromosomes();
        for (int i = 0; i < chromosomeLength; i++) {
            if (i >= ((crossoverPoint + 1) * 16) && (i < chromosomeLength - 16)) {
                assertThat(chromosomePair.getFirst().getGene(i), is(beforeCrossover.getSecond().getGene(i)));
                assertThat(chromosomePair.getSecond().getGene(i), is(beforeCrossover.getFirst().getGene(i)));
            } else {
                assertThat(chromosomePair.getFirst().getGene(i), is(beforeCrossover.getFirst().getGene(i)));
                assertThat(chromosomePair.getSecond().getGene(i), is(beforeCrossover.getSecond().getGene(i)));
            }
        }
    }
}