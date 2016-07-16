package music.analysis.feature.processor.statistics.intervals;

import music.notes.pitch.Interval;

import java.util.HashSet;
import java.util.Set;

/**
 * Analyze repeated intervals (with same semitones difference) in melody.
 * <ul>
 * <li>Numerator: number of repeated intervals</li>
 * <li>Denominator: number of all intervals</li>
 * </ul>
 * Assumptions:
 * <ul>
 * <li>0.0 - distinct intervals</li>
 * <li>1.0 - same interval in whole melody</li>
 * </ul>
 */
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
