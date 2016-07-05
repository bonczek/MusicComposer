package music.analysis.feature.type;

import music.analysis.feature.name.MelodicFeatureName;
import music.analysis.feature.processor.MelodicFeatureCounter;

/**
 * Basic type of feature that can be analyzed in melody.
 *
 * @param <T> type of number to store variables
 */
public abstract class MelodicFeature<T extends Number> {

    protected MelodicFeatureName name;

    protected T featureWeight;

    protected MelodicFeatureCounter<T> noteProcessor;

    public MelodicFeatureCounter<T> getNoteProcessor() {
        return noteProcessor;
    }

    public MelodicFeatureName getName() {
        return name;
    }

    public T getFeatureWeight() {
        return featureWeight;
    }

    public T getFeatureResult() {
        return noteProcessor.getResult();
    }

    public abstract String getReport();

}
