package music.analysis.feature.container;

import genetic.fitness.calculator.FitnessCalculator;
import genetic.fitness.calculator.RuleFitnessCalculator;
import music.analysis.feature.type.RuleFeature;

import java.util.List;

public class RuleContainer extends FeatureContainer<RuleFeature> {

    private static final FitnessCalculator<RuleFeature> FITNESS_CALCULATOR = new RuleFitnessCalculator();

    public RuleContainer(List<RuleFeature> rulesList) {
        super(rulesList, FITNESS_CALCULATOR);
    }

}
