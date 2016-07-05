package music.analysis.feature.container;

import genetic.fitness.Fitness;
import genetic.fitness.rules.RuleFitness;
import music.analysis.feature.type.RuleFeature;
import music.notes.Note;

import java.util.List;

public class RuleContainer implements FeatureContainer {

    private List<RuleFeature> rules;

    public RuleContainer(List<RuleFeature> rulesList) {
        this.rules = rulesList;
    }

    @Override
    public void applyFeatureProcessors(List<Note> melodyLine) {
        rules.forEach(r -> r.getNoteProcessor().clear());
        for (Note note : melodyLine) {
            rules.stream().forEach(r -> r.getNoteProcessor().processNote(note));
        }
    }

    @Override
    public Fitness getRewardSum() {
        RuleFitness ruleFitness = new RuleFitness();
        rules.forEach(ruleFitness::addRuleReward);
        return ruleFitness;
    }
}
