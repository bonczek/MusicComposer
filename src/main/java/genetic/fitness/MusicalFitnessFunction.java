package genetic.fitness;

import genetic.representation.Chromosome;
import genetic.util.Converter;
import music.analysis.feature.container.FeatureContainer;
import music.notes.Note;

import java.util.List;

public class MusicalFitnessFunction<T extends FeatureContainer> extends FitnessFunction {

    private T featureContainer;

    public MusicalFitnessFunction(T featureContainer) {
        this.featureContainer = featureContainer;
    }

    @Override
    protected Fitness rateChromosome(Chromosome chromosome) {
        return rateMelody(Converter.fromChromosome(chromosome));
    }

    public Fitness rateMelody(List<Note> noteList) {
        featureContainer.applyFeatureProcessors(noteList);
        return featureContainer.getRewardSum();
    }
}
