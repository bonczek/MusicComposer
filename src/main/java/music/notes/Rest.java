package music.notes;

public class Rest extends Note {

    public Rest(double rhythmValue) {
        super(rhythmValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rest rest = (Rest) o;

        return Double.compare(rest.rhythmValue, rhythmValue) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(rhythmValue);
        return (int) (temp ^ (temp >>> 32));
    }

}
