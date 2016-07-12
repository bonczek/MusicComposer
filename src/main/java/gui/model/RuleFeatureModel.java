package gui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import music.analysis.feature.name.RuleName;
import music.analysis.feature.type.RuleFeature;

public class RuleFeatureModel {
    private ObjectProperty<RuleName> ruleName;
    private BooleanProperty isActive;
    private StringProperty weight;

    public RuleFeatureModel() {
    }

    public RuleFeatureModel(RuleFeature ruleFeature) {
        RuleName ruleName = (RuleName) ruleFeature.getName();
        this.ruleName = new SimpleObjectProperty<>(ruleName);
        this.weight = new SimpleStringProperty(ruleFeature.getFeatureWeight().toString());
        this.isActive = new SimpleBooleanProperty(true);
    }

    public RuleName getRuleName() {
        return ruleName.get();
    }

    public void setRuleName(RuleName ruleName) {
        this.ruleName.set(ruleName);
    }

    public ObjectProperty<RuleName> ruleNameProperty() {
        return ruleName;
    }

    public boolean getIsActive() {
        return isActive.get();
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public StringProperty weightProperty() {
        return weight;
    }
}
