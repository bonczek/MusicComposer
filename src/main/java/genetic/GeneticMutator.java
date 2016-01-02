package genetic;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
interface GeneticMutator<T extends Object> {

    /**
     * Mutate chromosomes.
     *
     * @param chromosomes mutation candidates
     */
    void mutate(List<Chromosome<T>> chromosomes);

}
