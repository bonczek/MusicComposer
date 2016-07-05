package music.analysis.feature.processor.statistics.density;

import music.analysis.feature.processor.statistics.StatisticCounter;

public abstract class RhythmDensityStatistic extends StatisticCounter<Double> {

    public RhythmDensityStatistic() {
        super(0.0, 0.0);
    }

    @Override
    public void clear() {
        numerator = 0.0;
        denominator = 0.0;
    }
}
