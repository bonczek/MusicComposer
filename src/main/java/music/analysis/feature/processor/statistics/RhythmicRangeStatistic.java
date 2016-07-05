package music.analysis.feature.processor.statistics;

import music.notes.Note;

public class RhythmicRangeStatistic extends StatisticCounter<Integer> {

    private double minRhythmValue = Double.MAX_VALUE;
    private double maxRhythmValue = Double.MIN_VALUE;

    public RhythmicRangeStatistic(int denominator) {
        super(0, denominator);
    }

    public double getMinRhythmValue() {
        return minRhythmValue;
    }

    public double getMaxRhythmValue() {
        return maxRhythmValue;
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

    @Override
    public void clear() {
        numerator = 0;
        minRhythmValue = Double.MAX_VALUE;
        maxRhythmValue = Double.MIN_VALUE;
    }
}
