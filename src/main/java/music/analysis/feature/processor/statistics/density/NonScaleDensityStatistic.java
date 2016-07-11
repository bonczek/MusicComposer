package music.analysis.feature.processor.statistics.density;

import music.harmony.Harmony;
import music.notes.Note;
import music.notes.Sound;

public class NonScaleDensityStatistic extends RhythmDensityStatistic {

    private final Harmony scale;

    public NonScaleDensityStatistic(Harmony scale) {
        super();
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

}
