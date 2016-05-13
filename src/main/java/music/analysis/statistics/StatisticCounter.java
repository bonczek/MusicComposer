package music.analysis.statistics;

public abstract class StatisticCounter<T extends Number> implements MusicalStatistic {

    protected T numerator;

    protected T denominator;

    public StatisticCounter(T numerator, T denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double getResult() {
        return numerator.doubleValue() / denominator.doubleValue();
    }
}
