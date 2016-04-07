package music.analysis.towsey.statistics;

import music.Note;

public abstract class TowseyStatistic {

    protected int numerator = 0;

    protected int denominator;

    public TowseyStatistic(int denominator) {
        this.denominator = denominator;
    }

    public abstract void processNote(Note note);

    public double getResult() {
        return (double) numerator / (double) denominator;
    }

}
