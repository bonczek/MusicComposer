package music.analysis.feature.container;

import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.type.FeatureFitness;
import genetic.fitness.type.Fitness;
import music.analysis.feature.type.MelodicFeature;
import music.notes.Note;

import java.util.List;

/**
 * Container for features that will be analysed in melody line.
 *
 * @param <T> type of melodic feature
 */
public abstract class FeatureContainer<T extends MelodicFeature> {

    /**
     * Calculator that transform feature result to fitness/reward.
     */
    private final FitnessCalculator<T> fitnessCalculator;

    /**
     * List of features to analyze in melody line.
     */
    private List<T> featureList;

    public FeatureContainer(List<T> rulesList, FitnessCalculator<T> fitnessCalculator) {
        this.featureList = rulesList;
        this.fitnessCalculator = fitnessCalculator;
    }

    /**
     * Apply searching for features in a given melody line.
     * At the beginning reset state of each feature counter/processor.
     * Then iterate through list of notes and update feature counters.
     *
     * @param melodyLine list of notes to analyze
     */
    public void applyFeatureProcessors(List<Note> melodyLine) {
        featureList.forEach(f -> f.getNoteProcessor().clear());
        for (Note note : melodyLine) {
            featureList.stream().forEach(f -> f.getNoteProcessor().processNote(note));
        }
    }

    /**
     * Transform feature counters results into reward using fitness calculator and then sum reward for all features.
     *
     * @return final reward/fitness for analyzed melody line
     */
    public Fitness getRewardSum() {
        FeatureFitness<T> fitness = new FeatureFitness<>(fitnessCalculator);
        featureList.forEach(fitness::addFeatureReward);
        return fitness;
    }
}
