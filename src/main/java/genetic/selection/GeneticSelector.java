package genetic.selection;

import genetic.Chromosome;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public interface GeneticSelector<T extends Object> {

    List<Chromosome<T>> selectChromosomes(List<Chromosome<T>> population, List<Integer> fitnessValues);
}
