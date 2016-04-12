package music;

public abstract class Note {

    protected double rhythmValue;

    public Note(double rhythmValue) {
        this.rhythmValue = rhythmValue;
    }

    public double getRhythmValue() {
        return rhythmValue;
    }

    public void setRhythmValue(double rhythmValue) {
        this.rhythmValue = rhythmValue;
    }

    public void addRhytmValue(double rhythmValue) {
        this.rhythmValue += rhythmValue;
    }
}
