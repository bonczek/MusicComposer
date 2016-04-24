package music.notes;

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

    public void addRhythmValue(double rhythmValue) {
        this.rhythmValue += rhythmValue;
    }
}
