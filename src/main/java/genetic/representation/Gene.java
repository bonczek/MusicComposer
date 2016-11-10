package genetic.representation;

/**
 * Gene is part of genotype, that it's coded in {@link Chromosome}.
 * It is a single int value.
 */
public class Gene {

    private int value;

    public Gene(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gene gene = (Gene) o;

        return value == gene.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
