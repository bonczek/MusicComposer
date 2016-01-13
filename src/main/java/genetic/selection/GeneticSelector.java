package genetic.selection;

import genetic.representation.Chromosome;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public interface GeneticSelector {

    List<Chromosome> selectChromosomes(List<Chromosome> population);
}
