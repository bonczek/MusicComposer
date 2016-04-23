package genetic.selection;

import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;

import java.util.List;

public interface GeneticSelector {

    ChromosomePair selectChromosomes(List<Chromosome> population);
}
