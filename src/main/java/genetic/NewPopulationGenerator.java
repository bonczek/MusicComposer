package genetic;

import genetic.crossover.GeneticCrossover;
import genetic.mutation.GeneticMutation;
import genetic.representation.Chromosome;
import genetic.selection.GeneticSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public class NewPopulationGenerator {

    private final GeneticSelector geneticSelector;
    private final GeneticMutation geneticMutation;
    private final GeneticCrossover geneticCrossover;
    private List<Chromosome> newPopulation;

    public NewPopulationGenerator(GeneticSelector geneticSelector, GeneticMutation geneticMutation, GeneticCrossover geneticCrossover) {
        this.geneticSelector = geneticSelector;
        this.geneticMutation = geneticMutation;
        this.geneticCrossover = geneticCrossover;
        this.newPopulation = new ArrayList<>();
    }

    List<Chromosome> generateNewPopulation(List<Chromosome> population, List<Integer> fitnessValues) {
        int populationSize = population.size();

        while (newPopulation.size() < populationSize) {
            List<Chromosome> selectedChromosomes = geneticSelector.selectChromosomes(population, fitnessValues);
            geneticCrossover.crossOver(selectedChromosomes);
            geneticMutation.mutate(selectedChromosomes);

            selectedChromosomes.forEach(newPopulation::add);
        }
        return newPopulation;
    }

}
