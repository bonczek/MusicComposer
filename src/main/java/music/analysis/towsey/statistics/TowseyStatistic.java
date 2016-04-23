package music.analysis.towsey.statistics;

import music.notes.Note;

public abstract class TowseyStatistic {

    protected int numerator = 0;

    protected int denominator = 0;

    public TowseyStatistic() {
    }

    public TowseyStatistic(int denominator) {
        this.denominator = denominator;
    }

    public abstract void processNote(Note note);

    public void clear() {
        numerator = 0;
    }

    public double getResult() {
        return (double) numerator / (double) denominator;
    }

}
