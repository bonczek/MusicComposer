package music.analysis.feature.calculator;

import music.analysis.feature.type.MelodicFeature;

public interface RewardCalculator<T extends MelodicFeature> {

    /**
     * Transform result from melodic feature to reward used in GA.
     *
     * @param melodicFeature measured feature in melody line
     * @return reward/fitness value
     */
    int calculateReward(T melodicFeature);
}
