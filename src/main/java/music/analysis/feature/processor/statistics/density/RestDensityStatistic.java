package music.analysis.feature.processor.statistics.density;

import music.notes.Note;
import music.notes.Rest;

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
