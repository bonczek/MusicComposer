package music.analysis.feature.container;

import music.analysis.feature.calculator.RewardCalculator;
import music.analysis.feature.calculator.RuleFitnessCalculator;
import music.analysis.feature.type.RuleFeature;

import java.util.List;

public class RuleContainer extends FeatureContainer<RuleFeature> {

    private static final RewardCalculator<RuleFeature> FITNESS_CALCULATOR = new RuleFitnessCalculator();

    public RuleContainer(List<RuleFeature> rulesList) {
        super(rulesList, FITNESS_CALCULATOR);
    }

}
