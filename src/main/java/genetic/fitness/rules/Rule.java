package genetic.fitness.rules;

public class Rule {

    private String name;

    private int weight;
    private int numberOfRuleFound;
    private int result;

    public Rule(String name, int weight, int numberOfRuleFound) {
        this.name = name;
        this.weight = weight;
        this.numberOfRuleFound = numberOfRuleFound;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumberOfRuleFound() {
        return numberOfRuleFound;
    }

    public Integer calculateReward() {
        result = numberOfRuleFound * weight;
        return result;
    }

    public String getReport() {
        return String.format("Rule %s - count: %d; weight: %d; reward: %d\n", name, numberOfRuleFound, weight, result);
    }
}
