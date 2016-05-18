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
        musicalRules.add(new MelodicIntervalRule(i -> !i.moreThanOctave() && i.perfectConsonance()));
        musicalRules.add(new MelodicIntervalRule(i -> !i.moreThanOctave() && i.imperfectConsonance()));
        musicalRules.add(new MelodicIntervalRule(i -> !i.moreThanOctave() && i.dissonance()));
        musicalRules.add(new MelodicIntervalRule(Interval::moreThanOctave));
    }

    @Override
    protected Fitness rateMelody(List<Note> noteList) {
        musicalRules.forEach(MusicalRule::clear);
        for (Note n : noteList) {
            musicalRules.stream().forEach(r -> r.processNote(n));
        }
        RuleFitness fitness = new RuleFitness();
        Rule perfect = new Rule("PERFECT_CONSONANCE", 80, musicalRules.get(0).ruleCount());
        Rule imperfect = new Rule("IMPERFECT_CONSONANCE", 60, musicalRules.get(1).ruleCount());
        Rule dissonance = new Rule("DISSONANCE", 30, musicalRules.get(2).ruleCount());
        Rule moreThanOctave = new Rule("MORE_THAN_OCTAVE", 0, musicalRules.get(3).ruleCount());

        fitness.addRuleReward(perfect);
        fitness.addRuleReward(imperfect);
        fitness.addRuleReward(dissonance);
        fitness.addRuleReward(moreThanOctave);

        return fitness;
    }
}
