package music.analysis.feature.type;

import music.analysis.feature.MelodicFeatureCounter;
import music.analysis.feature.name.MelodicFeatureName;

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
