package genetic;

import genetic.crossover.CrossoverCoordinator;
import genetic.mutation.MutationCoordinator;
import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;
import genetic.selection.GeneticSelector;

import java.util.ArrayList;
import java.util.List;

public class NewPopulationGenerator {

    private final GeneticSelector geneticSelector;
    private final MutationCoordinator mutationCoordinator;
    private final CrossoverCoordinator crossoverCoordinator;

    public NewPopulationGenerator(GeneticSelector geneticSelector, MutationCoordinator mutationCoordinator, CrossoverCoordinator
            crossoverCoordinator) {
        this.geneticSelector = geneticSelector;
        this.mutationCoordinator = mutationCoordinator;
        this.crossoverCoordinator = crossoverCoordinator;
    }

    List<Chromosome> generateNewPopulation(List<Chromosome> population) {
        List<Chromosome> newPopulation = new ArrayList<>();
        int populationSize = population.size();

        while (newPopulation.size() < populationSize) {
            List<Chromosome> selectedChromosomes = geneticSelector.selectChromosomes(population);
            if (selectedChromosomes.size() == 2) {
                ChromosomePair crossoverCandidates = new ChromosomePair(selectedChromosomes.get(0),
                        selectedChromosomes.get(1));
                ChromosomePair crossedPair = crossoverCoordinator.crossoverWithProbability(crossoverCandidates);
                Chromosome firstAfterMutation = mutationCoordinator.mutateWithProbability(crossedPair.getFirst());
                Chromosome secondAfterMutation = mutationCoordinator.mutateWithProbability(crossedPair.getSecond());

                newPopulation.add(firstAfterMutation);
                newPopulation.add(secondAfterMutation);
            }
        }
        return newPopulation;
    }

}
