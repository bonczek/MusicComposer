package music.analysis.towsey.statistics;

import music.Note;

public class RhythmicRangeStatistic extends TowseyStatistic {

    private double minRhythmValue = Double.MAX_VALUE;

    private double maxRhythmValue = Double.MIN_VALUE;

    public RhythmicRangeStatistic(int denominator) {
        super(denominator);
    }

    @Override
    public void processNote(Note note) {
        if (note.getRhythmValue() > maxRhythmValue) {
            maxRhythmValue = note.getRhythmValue();
        }
        if (note.getRhythmValue() < minRhythmValue) {
            minRhythmValue = note.getRhythmValue();
        }
        double range = maxRhythmValue / minRhythmValue;
        numerator = (int) range;
    }
}
