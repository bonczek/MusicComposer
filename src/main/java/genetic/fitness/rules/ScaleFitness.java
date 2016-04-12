package genetic.fitness.rules;

import genetic.fitness.MusicalFitnessFunction;
import music.Harmony;
import music.Note;
import music.Sound;

import java.util.List;

public class ScaleFitness extends MusicalFitnessFunction {

    private int reward;

    private Harmony scale;

    public ScaleFitness(Harmony scale, int reward) {
        this.reward = reward;
        this.scale = scale;
    }

    @Override
    public Integer rateMelody(List<Note> noteList) {
        int rate = 0;
        for (Note note : noteList) {
            if (note instanceof Sound) {
                Sound sound = (Sound) note;
                if (scale.fit(sound)) {
                    rate += reward;
                }
            }
        }
        return rate;
    }
}
