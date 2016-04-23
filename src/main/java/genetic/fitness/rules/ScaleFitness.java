package genetic.fitness.rules;

import genetic.fitness.Fitness;
import genetic.fitness.MusicalFitnessFunction;
import genetic.fitness.SimpleReward;
import music.Harmony;
import music.notes.Note;
import music.notes.Sound;

import java.util.List;

public class ScaleFitness extends MusicalFitnessFunction {

    private int reward;

    private Harmony scale;

    public ScaleFitness(Harmony scale, int reward) {
        this.reward = reward;
        this.scale = scale;
    }

    @Override
    protected Fitness rateMelody(List<Note> noteList) {
        SimpleReward rate = new SimpleReward();
        for (Note note : noteList) {
            if (note instanceof Sound) {
                Sound sound = (Sound) note;
                if (scale.fit(sound.getPitch())) {
                    rate.addReward(reward);
                }
            }
        }
        return rate;
    }
}
