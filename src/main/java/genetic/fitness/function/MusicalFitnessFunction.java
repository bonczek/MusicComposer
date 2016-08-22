package genetic.fitness.function;

import genetic.fitness.Fitness;
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
    public Fitness rateChromosome(Chromosome chromosome) {
        return rateMelody(Converter.fromChromosome(chromosome));
    }

    public Fitness rateMelody(List<Note> noteList) {
        Fitness fitness = new Fitness();
        fitness.setFitnessValue(featureContainer.calculateReward(noteList));
        return fitness;
    }

    @Override
    public String createFitnessReport(Chromosome chromosome) {
        return createMelodyReport(Converter.fromChromosome(chromosome));
    }

    public String createMelodyReport(List<Note> noteList) {
        return featureContainer.createFitnessReport(noteList);
    }

    public String createAnalysisReport(List<Note> noteList) {
        return featureContainer.createAnalysisReport(noteList);
    }

}
