package music.analysis.rules;

import music.notes.pitch.Interval;

import java.util.function.Function;

public class MelodicIntervalRule extends IntervalRule {

    private Function<Interval, Boolean> function;

    public MelodicIntervalRule(Function<Interval, Boolean> function) {
        this.function = function;
    }

    @Override
    protected void processInterval(Interval interval) {
        if (function.apply(interval)) {
            ruleCounter++;
        }
    }
}
