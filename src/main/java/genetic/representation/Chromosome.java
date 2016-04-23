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
 * There is no size limit for chromosome. It can be single measure of music or whole composition, but it should be
 * multiplication of NOTES_IN_MEASURE.
 */
public class Chromosome {

    protected final List<Gene> genes;
    private final int size;
    private Fitness fitness;

    public Chromosome(List<Gene> genes) {
        this.genes = genes;
        this.size = genes.size();
    }

    //@todo can be moved to other place - it is used only in tests
    public static Chromosome createWithIntegerValues(List<Integer> values) {
        List<Gene> genes = values.stream().map(value -> new Gene(value.shortValue())).collect(Collectors.toList());
        return new Chromosome(genes);
    }

    public static Chromosome createCopy(Chromosome chromosome) {
        return new Chromosome(chromosome.getPart(0, chromosome.getSize()));
    }

    public Fitness getFitness() {
        return fitness;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    public List<Integer> getGenesValues() {
        return genes.stream().map(gene -> gene.getValue().intValue()).collect(Collectors.toList());
    }

    public int getSize() {
        return size;
    }

    public Gene getGene(int index) throws IndexOutOfBoundsException {
        if (index < size) {
            return genes.get(index);
        } else {
            throw new IndexOutOfBoundsException(String.format("Cannot find in chromosome gen with index: %d.", index));
        }
    }

    public void setGene(int index, Gene value) throws IndexOutOfBoundsException {
        if (index < size) {
            genes.set(index, value);
        } else {
            throw new IndexOutOfBoundsException(String.format("Cannot find in chromosome gen with index: %d.", index));
        }
    }

    public List<Gene> getPart(int fromIndex, int toIndex) {
        //@todo toIndex > fromIndex ...
        if (toIndex < size + 1 && fromIndex >= 0) {
            return new ArrayList<>(genes.subList(fromIndex, toIndex));
        } else {
            throw new IndexOutOfBoundsException(String.format("Cannot find in chromosome gen with index: %d.", toIndex));
        }
    }

    public void setPart(List<Gene> part, int fromIndex, int toIndex) {
        //part.size < toIndex - fromIndex
        if (toIndex < size + 1 && fromIndex >= 0) {
            int i = fromIndex;
            for (Gene gene : part) {
                genes.set(i, gene);
                i++;
            }
        }
    }

    @Override
    public String toString() {
        return genes.stream().map(Gene::getValue).map(Object::toString).collect(Collectors.joining("|"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        //@todo
        Chromosome that = (Chromosome) o;

        if (size != that.size) return false;
        for (int i = 0; i < size; i++) {
            if (!genes.get(i).getValue().equals(that.genes.get(i).getValue())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        //@todo
        int result = genes.hashCode();
        result = 31 * result + size;
        return result;
    }

    public Chromosome mutate(Mutation mutation) {
        return mutation.mutate(this);
    }
}
