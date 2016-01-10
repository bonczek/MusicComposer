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

    public void setValue(Short value) {
        this.value = value;
    }
}
