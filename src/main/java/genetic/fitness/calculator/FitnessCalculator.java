package genetic.fitness.calculator;

import music.analysis.feature.type.MelodicFeature;

public interface FitnessCalculator<T extends MelodicFeature> {

    int calculateReward(T melodicFeature);
}
