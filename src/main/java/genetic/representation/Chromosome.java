package genetic.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adam on 02.01.16.
 */
public class Chromosome {

    protected final List<Gene> genes;
    private final int size;

    public Chromosome(List<Gene> genes) {
        this.genes = genes;
        this.size = genes.size();
    }

    public static Chromosome createWithIntegerValues(List<Integer> values) {
        List<Gene> genes = values.stream().map(value -> new Gene(value.shortValue())).collect(Collectors.toList());
        return new Chromosome(genes);
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

}
