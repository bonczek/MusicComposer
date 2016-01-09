package genetic;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<T> getGenes() {
        return Collections.unmodifiableList(genes);
    }

    public int getSize() {
        return size;
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

    public List<T> getPart(int fromIndex, int toIndex) {
        //@todo toIndex > fromIndex ...
        if (toIndex < size + 1 && fromIndex >= 0) {
            return new ArrayList<>(genes.subList(fromIndex, toIndex));
        } else {
            throw new IndexOutOfBoundsException(String.format("Cannot find in chromosome gen with index: %d.", toIndex));
        }
    }

    public void setPart(List<T> part, int fromIndex, int toIndex) {
        //part.size < toIndex - fromIndex
        if (toIndex < size + 1 && fromIndex >= 0) {
            int i = fromIndex;
            for (T gene : part) {
                genes.set(i, gene);
                i++;
            }
        }
    }

}
