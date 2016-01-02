package genetic.selection;

import genetic.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adam on 02.01.16.
 */
public class BinaryTournamentSelection<T extends Object> implements GeneticSelector<T> {

    private final Random numberGenerator;

    public BinaryTournamentSelection(Random numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public List<Chromosome<T>> selectChromosomes(List<Chromosome<T>> population, List<Integer> fitnessValues) {
        List<Chromosome<T>> selectedChromosomes = new ArrayList<>();
        selectedChromosomes.add(binaryTournamentSelection(population, fitnessValues));
        selectedChromosomes.add(binaryTournamentSelection(population, fitnessValues));
        return selectedChromosomes;
    }

    private Chromosome<T> binaryTournamentSelection(List<Chromosome<T>> population, List<Integer> fitnessValues) {
        int firstParentIndex = numberGenerator.nextInt(population.size());
        Chromosome<T> firstParent = population.get(firstParentIndex);
        int secondParentIndex = numberGenerator.nextInt(population.size());
        Chromosome<T> secondParent = population.get(secondParentIndex);

        if (fitnessValues.get(firstParentIndex) > fitnessValues.get(secondParentIndex)) {
            return firstParent;
        } else {
            return secondParent;
        }
    }
}
