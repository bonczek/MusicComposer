package music.analysis.feature.name;

public enum RuleName implements MelodicFeatureName {
    PERFECT_CONSONANCE(RuleType.INTERVAL), IMPERFECT_CONSONANCE(RuleType.INTERVAL), MAJOR_OR_MINOR_SECOND(RuleType.INTERVAL),
    DISSONANCE(RuleType.INTERVAL), LESS_THAN_OCTAVE(RuleType.INTERVAL),
    WHOLE_NOTE(RuleType.RHYTHMICAL), HALF_NOTE(RuleType.RHYTHMICAL), QUARTER_NOTE(RuleType.RHYTHMICAL), EIGHT_NOTE(RuleType.RHYTHMICAL),
    SIXTEENTH_NOTE(RuleType.RHYTHMICAL),
    ONE_LINED_GRAVITY(RuleType.TIME_BASED), SCALE_NOTE(RuleType.TIME_BASED), CHORD_NOTE(RuleType.TIME_BASED),
    REST_NOTE(RuleType.TIME_BASED),
    STRONG_BEAT(RuleType.OTHER);

    private RuleType type;

    RuleName(RuleType type) {
        this.type = type;
    }

    public boolean isGivenType(RuleType type) {
        return this.type.equals(type);
    }
}
