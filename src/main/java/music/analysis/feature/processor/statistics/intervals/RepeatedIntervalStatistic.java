package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

import java.util.HashSet;
import java.util.Set;

public class RepeatedIntervalStatistic extends IntervalStatistic {

    private Set<Integer> semitoneDifferencesSet = new HashSet<>();

    @Override
    protected void processInterval(Interval interval) {
        int semitonesDifference = Math.abs(interval.getSemitonesDifference());
        if (semitoneDifferencesSet.contains(semitonesDifference)) {
            numerator++;
        } else {
            semitoneDifferencesSet.add(semitonesDifference);
        }
        denominator++;
    }

    @Override
    public void clear() {
        super.clear();
        semitoneDifferencesSet.clear();
    }

    public int getIntervalSetSize() {
        return semitoneDifferencesSet.size();
    }
}
