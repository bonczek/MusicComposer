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
        Chromosome first = binaryTournamentSelection(population);
        Chromosome second = binaryTournamentSelection(population);
        return new ChromosomePair(first, second);
    }

    private Chromosome binaryTournamentSelection(List<Chromosome> population) {
        int firstParentIndex = numberGenerator.nextInt(population.size());
        Chromosome firstParent = population.get(firstParentIndex);
        int secondParentIndex = numberGenerator.nextInt(population.size());
        Chromosome secondParent = population.get(secondParentIndex);

        if (firstParent.getFitness().getFitnessValue() > secondParent.getFitness().getFitnessValue()) {
            return new Chromosome(firstParent.getPart(0, firstParent.getSize()));
        } else {
            return new Chromosome(secondParent.getPart(0, secondParent.getSize()));
        }
    }
}
