package genetic;

import genetic.crossover.GeneticCrossover;
import genetic.mutation.MutationCoordinator;
import genetic.representation.Chromosome;
import genetic.selection.GeneticSelector;

import java.util.ArrayList;
import java.util.List;

public class NewPopulationGenerator {

    private final GeneticSelector geneticSelector;
    private final MutationCoordinator mutationCoordinator;
    private final GeneticCrossover geneticCrossover;

    public NewPopulationGenerator(GeneticSelector geneticSelector, MutationCoordinator mutationCoordinator, GeneticCrossover
            geneticCrossover) {
        this.geneticSelector = geneticSelector;
        this.mutationCoordinator = mutationCoordinator;
        this.geneticCrossover = geneticCrossover;
    }

    List<Chromosome> generateNewPopulation(List<Chromosome> population) {
        List<Chromosome> newPopulation = new ArrayList<>();
        int populationSize = population.size();

        while (newPopulation.size() < populationSize) {
            List<Chromosome> selectedChromosomes = geneticSelector.selectChromosomes(population);
            geneticCrossover.crossOver(selectedChromosomes);
            selectedChromosomes.stream().map(mutationCoordinator::mutateWithProbability).forEach(newPopulation::add);
        }
        return newPopulation;
    }

}
