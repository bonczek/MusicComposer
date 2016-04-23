package music.analysis.towsey.statistics;

import genetic.initial.InitialPopulationGenerator;
import genetic.util.Converter;
import music.Harmony;
import music.notes.Note;
import music.notes.Sound;

public class NonScaleStatistic extends TowseyStatistic {

    private Harmony scale;

    public NonScaleStatistic(int numbersOfMeasures, Harmony scale) {
        super(numbersOfMeasures * InitialPopulationGenerator.NOTES_IN_MEASURE);
        this.scale = scale;
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            if (!scale.fit(((Sound) note).getPitch())) {
                double rhythmValue = note.getRhythmValue();
                while (rhythmValue > 0) {
                    numerator++;
                    rhythmValue -= Converter.DEFAULT_RHYTHMIC_VALUE;
                }
            }
        }

    }
}
