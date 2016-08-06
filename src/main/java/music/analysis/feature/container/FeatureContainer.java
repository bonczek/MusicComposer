package music.analysis.feature.container;

import genetic.fitness.Fitness;
import genetic.fitness.calculator.FitnessCalculator;
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

    public Fitness calculateReward(List<Note> melody) {
        applyFeatureProcessors(melody);
        return getRewardSum();
    }

    public String createFitnessReport(List<Note> melody) {
        StringBuilder reportBuilder = new StringBuilder();
        applyFeatureProcessors(melody);
        featureList.forEach(feature -> reportBuilder.append(String.format("%s reward: %d\n", feature.getReport(),
                fitnessCalculator.calculateReward(feature))));
        return reportBuilder.toString();
    }

    /**
     * Apply searching for features in a given melody line.
     * At the beginning reset state of each feature counter/processor.
     * Then iterate through list of notes and update feature counters.
     *
     * @param melodyLine list of notes to analyze
     */
    private void applyFeatureProcessors(List<Note> melodyLine) {
        featureList.forEach(f -> f.getNoteProcessor().clear());
        melodyLine.forEach(this::applyFeaturesToSingleNote);
    }

    private void applyFeaturesToSingleNote(Note note) {
        for (T feature : featureList) {
            try {
                feature.getNoteProcessor().processNote(note);
            } catch (Exception e) {
                System.out.println(String.format(
                        "Failed to calculate feature %s, because: %s", feature.getName(), e.getMessage()));
            }
        }
    }

    /**
     * Calculate reward using fitness calculator and then sum reward for all features.
     *
     * @return final reward/fitness for analyzed melody line
     */
    private Fitness getRewardSum() {
        Fitness fitness = new Fitness();
        //@todo maybe sum and set value?
        featureList.stream().map(fitnessCalculator::calculateReward).forEach(fitness::addReward);
        return fitness;
    }
}
