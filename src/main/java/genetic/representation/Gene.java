package genetic.representation;

/**
 * Created by adam on 10.01.16.
 */
public class Gene {

    private Short value;

    public Gene(Short value) {
        this.value = value;
    }

    public Gene(Integer value) {
        this.value = value.shortValue();
    }

    public Short getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value.shortValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gene gene = (Gene) o;

        if (!value.equals(gene.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
