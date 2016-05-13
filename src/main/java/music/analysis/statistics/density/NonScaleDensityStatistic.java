package music.analysis.statistics.density;

import music.Harmony;
import music.notes.Note;
import music.notes.Sound;

public class NonScaleDensityStatistic extends RhythmDensityStatistic {

    private Harmony scale;

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
