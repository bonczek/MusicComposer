package genetic.fitness.rules;

import genetic.fitness.Fitness;

public class RuleFitness extends Fitness {

    private StringBuilder reportBuilder = new StringBuilder();

    public void addRuleReward(Rule rule) {
        fitnessValue += rule.calculateReward();
        reportBuilder.append(rule.getReport());
    }

    @Override
    public String getReport() {
        return reportBuilder.toString();
    }
}
