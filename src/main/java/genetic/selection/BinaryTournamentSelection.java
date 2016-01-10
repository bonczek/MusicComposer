package genetic.selection;

import genetic.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adam on 02.01.16.
 */
public class BinaryTournamentSelection implements GeneticSelector {

    private final Random numberGenerator;

    public BinaryTournamentSelection(Random numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public List<Chromosome> selectChromosomes(List<Chromosome> population, List<Integer> fitnessValues) {
        List<Chromosome> selectedChromosomes = new ArrayList<>();
        selectedChromosomes.add(binaryTournamentSelection(population, fitnessValues));
        selectedChromosomes.add(binaryTournamentSelection(population, fitnessValues));
        return selectedChromosomes;
    }

    private Chromosome binaryTournamentSelection(List<Chromosome> population, List<Integer> fitnessValues) {
        int firstParentIndex = numberGenerator.nextInt(population.size());
        Chromosome firstParent = population.get(firstParentIndex);
        int secondParentIndex = numberGenerator.nextInt(population.size());
        Chromosome secondParent = population.get(secondParentIndex);

        if (fitnessValues.get(firstParentIndex) > fitnessValues.get(secondParentIndex)) {
            return firstParent;
        } else {
            return secondParent;
        }
    }
}
