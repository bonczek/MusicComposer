package music.analysis.statistics.density;

import music.analysis.statistics.StatisticCounter;

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
