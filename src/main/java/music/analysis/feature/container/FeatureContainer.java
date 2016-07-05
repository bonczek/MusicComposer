package music.analysis.feature.container;

import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.type.FeatureFitness;
import genetic.fitness.type.Fitness;
import music.analysis.feature.type.MelodicFeature;
import music.notes.Note;

import java.util.List;

public abstract class FeatureContainer<T extends MelodicFeature> {

    private final FitnessCalculator<T> fitnessCalculator;

    private List<T> featureList;

    public FeatureContainer(List<T> rulesList, FitnessCalculator<T> fitnessCalculator) {
        this.featureList = rulesList;
        this.fitnessCalculator = fitnessCalculator;
    }

    public void applyFeatureProcessors(List<Note> melodyLine) {
        featureList.forEach(f -> f.getNoteProcessor().clear());
        for (Note note : melodyLine) {
            featureList.stream().forEach(f -> f.getNoteProcessor().processNote(note));
        }
    }

    public Fitness getRewardSum() {
        FeatureFitness<T> fitness = new FeatureFitness<>(fitnessCalculator);
        featureList.forEach(fitness::addFeatureReward);
        return fitness;
    }
}
