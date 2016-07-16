package music.analysis.feature.processor.statistics.density;

import music.notes.Note;
import music.notes.Rest;

/**
 * Analyze rest density in melody.
 * <ul>
 * <li>Numerator: rest time in melody</li>
 * <li>Denominator: time of complete melody</li>
 * </ul>
 * Assumptions:
 * <ul>
 * <li>0.0 - melody doesn't have rests</li>
 * <li>1.0 - melody has only rests</li>
 * </ul>
 */
public class RestDensityStatistic extends RhythmDensityStatistic {

    public RestDensityStatistic() {
        super();
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Rest) {
            numerator += note.getRhythmValue();
        }
        denominator += note.getRhythmValue();
    }
}
