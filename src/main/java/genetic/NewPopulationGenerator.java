package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adam on 02.01.16.
 */
public class NewPopulationGenerator<T extends Object> {

    private final GeneticSelector<T> geneticSelector;
    private final GeneticMutator<T> geneticMutator;
    private final GeneticCrossover<T> geneticCrossover;
    private List<Chromosome<T>> newPopulation;

    public NewPopulationGenerator(GeneticSelector<T> geneticSelector, GeneticMutator<T> geneticMutator, GeneticCrossover<T> geneticCrossover) {
        this.geneticSelector = geneticSelector;
        this.geneticMutator = geneticMutator;
        this.geneticCrossover = geneticCrossover;
        this.newPopulation = new ArrayList<>();
    }

    List<Chromosome<T>> generateNewPopulation(Map<Chromosome<T>, Integer> populationWithFitness) {
        int populationSize = populationWithFitness.size();

        while (newPopulation.size() < populationSize) {
            List<Chromosome<T>> selectedChromosomes = geneticSelector.selectChromosomes(populationWithFitness);
            geneticCrossover.crossOverChromosomes(selectedChromosomes);
            geneticMutator.mutate(selectedChromosomes);

            selectedChromosomes.forEach(newPopulation::add);
        }
        return newPopulation;
    }

}
