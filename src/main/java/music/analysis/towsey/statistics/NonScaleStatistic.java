package music.analysis.towsey.statistics;

import music.Harmony;
import music.notes.Note;
import music.notes.Sound;

public class NonScaleStatistic extends StatisticCounter<Double> {

    private Harmony scale;

    public NonScaleStatistic(Harmony scale) {
        super(0.0, 0.0);
        this.scale = scale;
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            if (!scale.fit(((Sound) note).getPitch())) {
                numerator += note.getRhythmValue();
            }
        }
        denominator += note.getRhythmValue();
    }

    @Override
    public void clear() {
        numerator = 0.0;
        denominator = 0.0;
    }
}
