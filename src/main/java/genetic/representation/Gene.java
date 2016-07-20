package genetic.representation;

/**
 * Gene is representation of single note/rest/tenuto coded as number.
 * There is no information about type of note inside.
 * It should be parsed to {@link Note} after GA ends.
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

    @Override
    public String toString() {
        return "Gene{" +
                "value=" + value +
                '}';
    }
}
