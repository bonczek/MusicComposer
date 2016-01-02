package genetic;

import java.util.List;
import java.util.Map;

/**
 * Created by adam on 02.01.16.
 */
public interface GeneticSelector<T extends Object> {
    List<Chromosome<T>> selectChromosomes(Map<Chromosome<T>, Integer> populationWithFitness);
}
