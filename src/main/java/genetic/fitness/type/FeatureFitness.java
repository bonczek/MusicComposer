package genetic.fitness.type;

import genetic.fitness.calculator.FitnessCalculator;
import music.analysis.feature.type.MelodicFeature;

public class FeatureFitness<T extends MelodicFeature> extends Fitness {

    private StringBuilder reportBuilder = new StringBuilder();

    private FitnessCalculator<T> fitnessCalculator;

    public FeatureFitness(FitnessCalculator<T> fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }

    /**
     * Calculate reward for given feature and add to stored fitness value.
     * Additionally each feature add single line to report builder.
     *
     * @param feature which result will be added to fitness and report
     */
    public void addFeatureReward(T feature) {
        int featureReward = fitnessCalculator.calculateReward(feature);
        reportBuilder.append(String.format("%s; reward: %d\n", feature.getReport(), featureReward));
        fitnessValue += featureReward;
    }

    /**
     * Return report including each feature report and reward information or empty string if fitness doesn't have
     * any feature.
     *
     * @return report with all features results
     */
    @Override
    public String getReport() {
        return reportBuilder.toString();
    }
}
