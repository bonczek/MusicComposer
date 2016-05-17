package music.analysis.statistics;

import music.notes.Note;
import music.notes.Sound;


public class RepeatedSoundRhythmPairStatistic extends StatisticCounter<Integer> {

    private Sound previousSound;

    public RepeatedSoundRhythmPairStatistic() {
        super(0, 0);
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            if (previousSound == null) {
                previousSound = sound;
            } else {
                if (Double.compare(previousSound.getRhythmValue(), sound.getRhythmValue()) == 0) {
                    numerator++;
                }
                denominator++;
                previousSound = sound;
            }
        }
    }

    @Override
    public void clear() {
        numerator = 0;
        denominator = 0;
        previousSound = null;
    }
}
