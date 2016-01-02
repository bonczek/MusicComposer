package genetic;

import genetic.selection.GeneticSelector;

import java.util.ArrayList;
import java.util.List;

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

    List<Chromosome<T>> generateNewPopulation(List<Chromosome<T>> population, List<Integer> fitnessValues) {
        int populationSize = population.size();

        while (newPopulation.size() < populationSize) {
            List<Chromosome<T>> selectedChromosomes = geneticSelector.selectChromosomes(population, fitnessValues);
            geneticCrossover.crossOverChromosomes(selectedChromosomes);
            geneticMutator.mutate(selectedChromosomes);

            selectedChromosomes.forEach(newPopulation::add);
        }
        return newPopulation;
    }

}
