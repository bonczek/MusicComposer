package genetic;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
interface GeneticCrossover<T extends Object> {

    /**
     * Cross over given chromosomes.
     *
     * @param chromosomes cross over candidates
     */
    void crossOverChromosomes(List<Chromosome<T>> chromosomes);
}
