package music.analysis.feature.processor.statistics;

import music.analysis.feature.processor.DoubleFeatureCounter;

public abstract class StatisticCounter<T extends Number> implements DoubleFeatureCounter {

    protected T numerator;
    protected T denominator;

    public StatisticCounter(T numerator, T denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public T getNumerator() {
        return numerator;
    }

    public T getDenominator() {
        return denominator;
    }

    public Double getResult() {
        return numerator.doubleValue() / denominator.doubleValue();
    }
}
