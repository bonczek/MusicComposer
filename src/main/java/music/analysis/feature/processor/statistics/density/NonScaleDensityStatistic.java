package music.analysis.feature.processor.statistics.density;

import music.harmony.Harmony;
import music.notes.Note;
import music.notes.Sound;

/**
 * Statistic analyze time spend on scale or non scale note.
 * Numerator: time on scale note
 * Denominator: rhythm value of all sounds in melody
 * <p>
 * Assumptions:
 * 0.0 - only scale notes
 * 1.0 - only non-scale notes
 */
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
            denominator += note.getRhythmValue();
        }
    }

}
