package genetic.selection;

import genetic.representation.Chromosome;

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
    public List<Chromosome> selectChromosomes(List<Chromosome> population) {
        List<Chromosome> selectedChromosomes = new ArrayList<>();
        selectedChromosomes.add(binaryTournamentSelection(population));
        selectedChromosomes.add(binaryTournamentSelection(population));
        return selectedChromosomes;
    }

    private Chromosome binaryTournamentSelection(List<Chromosome> population) {
        int firstParentIndex = numberGenerator.nextInt(population.size());
        Chromosome firstParent = population.get(firstParentIndex);
        int secondParentIndex = numberGenerator.nextInt(population.size());
        Chromosome secondParent = population.get(secondParentIndex);

        if (firstParent.getFitness() > secondParent.getFitness()) {
            return firstParent;
        } else {
            return secondParent;
        }
    }
}
