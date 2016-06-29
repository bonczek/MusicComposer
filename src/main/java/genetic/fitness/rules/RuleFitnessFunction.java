package genetic.fitness.rules;

import genetic.fitness.Fitness;
import genetic.fitness.MusicalFitnessFunction;
import music.analysis.rules.MelodicIntervalRule;
import music.analysis.rules.MusicalRule;
import music.notes.Note;
import music.notes.pitch.Interval;

import java.util.ArrayList;
import java.util.List;

public class RuleFitnessFunction extends MusicalFitnessFunction {

    private List<MusicalRule> musicalRules = new ArrayList<>();

    public RuleFitnessFunction() {
        musicalRules.add(new MelodicIntervalRule(Interval::perfectConsonance));
        musicalRules.add(new MelodicIntervalRule(Interval::imperfectConsonance));
        musicalRules.add(new MelodicIntervalRule(Interval::dissonance));
        musicalRules.add(new MelodicIntervalRule(i -> !i.moreThanOctave()));
//        musicalRules.add(new NoteRule(n -> n.getRhythmValue() >= Durations.QUARTER_NOTE));
//        musicalRules.add(new NoteRule(n -> n instanceof Rest));
//        musicalRules.add(new NoteRule(n -> n instanceof Sound));

    }

    @Override
    public Fitness rateMelody(List<Note> noteList) {
        musicalRules.forEach(MusicalRule::clear);
        for (Note n : noteList) {
            musicalRules.stream().forEach(r -> r.processNote(n));
        }
        RuleFitness fitness = new RuleFitness();
        Rule perfect = new Rule("PERFECT_CONSONANCE", 50, musicalRules.get(0).ruleCount());
        Rule imperfect = new Rule("IMPERFECT_CONSONANCE", 50, musicalRules.get(1).ruleCount());
        Rule dissonance = new Rule("DISSONANCE", 30, musicalRules.get(2).ruleCount());
        Rule lessThanOctave = new Rule("LESS_THAN_OCTAVE", 60, musicalRules.get(3).ruleCount());
        Rule longNotes = new Rule("LONG_NOTES", 50, musicalRules.get(4).ruleCount());
        Rule restNotes = new Rule("REST_NOTES", 50, musicalRules.get(5).ruleCount());
        Rule soundNotes = new Rule("SOUND_NOTES", 50, musicalRules.get(6).ruleCount());

        fitness.addRuleReward(perfect);
        fitness.addRuleReward(imperfect);
        fitness.addRuleReward(dissonance);
        fitness.addRuleReward(lessThanOctave);
        fitness.addRuleReward(longNotes);
        fitness.addRuleReward(restNotes);
        fitness.addRuleReward(soundNotes);

        return fitness;
    }
}
