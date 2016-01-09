package genetic;

import genetic.crossover.GeneticCrossover;
import genetic.mutation.GeneticMutation;
import genetic.selection.GeneticSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public class NewPopulationGenerator<T extends Object> {

    private final GeneticSelector<T> geneticSelector;
    private final GeneticMutation<T> geneticMutation;
    private final GeneticCrossover<T> geneticCrossover;
    private List<Chromosome<T>> newPopulation;

    public NewPopulationGenerator(GeneticSelector<T> geneticSelector, GeneticMutation<T> geneticMutation, GeneticCrossover<T> geneticCrossover) {
        this.geneticSelector = geneticSelector;
        this.geneticMutation = geneticMutation;
        this.geneticCrossover = geneticCrossover;
        this.newPopulation = new ArrayList<>();
    }

    List<Chromosome<T>> generateNewPopulation(List<Chromosome<T>> population, List<Integer> fitnessValues) {
        int populationSize = population.size();

        while (newPopulation.size() < populationSize) {
            List<Chromosome<T>> selectedChromosomes = geneticSelector.selectChromosomes(population, fitnessValues);
            geneticCrossover.crossOver(selectedChromosomes);
            geneticMutation.mutate(selectedChromosomes);

            selectedChromosomes.forEach(newPopulation::add);
        }
        return newPopulation;
    }

}
