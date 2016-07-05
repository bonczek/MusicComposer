package music.analysis.feature.container;

import genetic.fitness.Fitness;
import music.notes.Note;

import java.util.List;

public interface FeatureContainer {

    void applyFeatureProcessors(List<Note> melodyLine);

    Fitness getRewardSum();
}
