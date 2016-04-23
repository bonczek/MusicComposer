package genetic.selection;

import genetic.representation.Chromosome;
import genetic.representation.ChromosomePair;

import java.util.List;
import java.util.Random;

public class BinaryTournamentSelection implements GeneticSelector {

    private final Random numberGenerator;

    public BinaryTournamentSelection(Random numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public ChromosomePair selectChromosomes(List<Chromosome> population) {
        Chromosome first = Chromosome.createCopy(binaryTournamentSelection(population));
        Chromosome second = Chromosome.createCopy(binaryTournamentSelection(population));
        return new ChromosomePair(first, second);
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
