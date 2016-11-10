package genetic.representation;

import genetic.fitness.Fitness;
import genetic.mutation.Mutation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Chromosome is main component of composition, it is a container for genes {@link Gene}.
 * GA applying crossover, mutation and selection operators on chromosomes and every chromosome can be measured by his
 * fitness.
 */
public class Chromosome {

    private final List<Gene> genes;
    private Fitness fitness;

    public Chromosome(List<Gene> genes) {
        this.genes = new ArrayList<>(genes);
        this.fitness = new Fitness();
    }

    public Fitness getFitness() {
        return fitness;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    public List<Integer> getGenesValues() {
        return genes.stream().map(Gene::getValue).collect(Collectors.toList());
    }

    public int getSize() {
        return genes.size();
    }

    public Gene getGene(int index) throws IndexOutOfBoundsException {
        Gene gene = genes.get(index);
        if (gene != null) {
            return new Gene(gene.getValue());
        } else {
            return null;
        }
    }

    public void setGene(int index, Gene gene) throws IndexOutOfBoundsException {
        genes.set(index, gene);
    }

    public List<Gene> getPart(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        return genes.subList(fromIndex, toIndex).stream().map(g -> new Gene(g.getValue())).collect(Collectors.toList());
    }

    public void setPart(List<Gene> part, int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        if (toIndex < genes.size() + 1 && fromIndex >= 0 && toIndex > fromIndex) {
            int i = fromIndex;
            for (Gene gene : part) {
                genes.set(i, gene);
                i++;
            }
        } else {
            throw new IndexOutOfBoundsException(String.format("Failed to set new values for genes in chromosome. " +
                    "Invalid indexes - start: %d, end: %d", fromIndex, toIndex));
        }
    }

    @Override
    public String toString() {
        return genes.stream().map(Gene::getValue).map(Object::toString).collect(Collectors.joining("|"));
    }

    public Chromosome mutate(Mutation mutation) {
        this.fitness.clearFitnessValue();
        return mutation.mutate(this);
    }
}
