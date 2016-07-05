package genetic.fitness.rules;

import genetic.fitness.Fitness;
import music.analysis.feature.type.RuleFeature;

public class RuleFitness extends Fitness {

    private StringBuilder reportBuilder = new StringBuilder();

    //@todo similar method to StatisticFitness
    public void addRuleReward(RuleFeature rule) {
        int featureReward = rule.getFeatureResult() * rule.getFeatureWeight();
        reportBuilder.append(String.format("%s; reward: %d", rule.getReport(), featureReward));
        fitnessValue += featureReward;
    }

    @Override
    public String getReport() {
        return reportBuilder.toString();
    }
}
