package genetic;

import java.util.List;

/**
 * Created by adam on 02.01.16.
 */
public class Chromosome<T extends Object> {

    protected final List<T> genes;
    private final int size;

    public Chromosome(List<T> genes) {
        this.genes = genes;
        this.size = genes.size();
    }

    public T getGene(int index) throws IndexOutOfBoundsException {
        if (index < size) {
            return genes.get(index);
        } else {
            throw new IndexOutOfBoundsException(String.format("Cannot find in chromosome gen with index: %d.", index));
        }
    }

    public void setGene(int index, T value) throws IndexOutOfBoundsException {
        if (index < size) {
            genes.set(index, value);
        } else {
            throw new IndexOutOfBoundsException(String.format("Cannot find in chromosome gen with index: %d.", index));
        }
    }
}
