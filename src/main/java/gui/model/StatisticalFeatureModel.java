package gui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import music.analysis.feature.name.StatisticName;
import music.analysis.feature.type.StatisticalFeature;

public class StatisticalFeatureModel {

    private ObjectProperty<StatisticName> statisticName;
    private BooleanProperty isActive;
    private StringProperty expectedValue;
    private StringProperty standardDeviation;
    private StringProperty weight;

    public StatisticalFeatureModel() {
    }

    public StatisticalFeatureModel(StatisticalFeature statisticalFeature) {
        StatisticName statisticName = (StatisticName) statisticalFeature.getName();
        this.statisticName = new SimpleObjectProperty<>(statisticName);
        this.expectedValue = new SimpleStringProperty(Double.toString(statisticalFeature.getExpectedValue()));
        this.weight = new SimpleStringProperty(Double.toString(statisticalFeature.getFeatureWeight()));
        this.standardDeviation = new SimpleStringProperty(Double.toString(statisticalFeature.getStandardDeviation()));
        this.isActive = new SimpleBooleanProperty(false);
    }

    public StatisticalFeatureModel(StatisticName statisticName, double expectedValue, double weight, double
            standardDeviation, boolean isActive) {
        this.statisticName = new SimpleObjectProperty<>(statisticName);
        this.expectedValue = new SimpleStringProperty(Double.toString(expectedValue));
        this.weight = new SimpleStringProperty(Double.toString(weight));
        this.standardDeviation = new SimpleStringProperty(Double.toString(standardDeviation));
        this.isActive = new SimpleBooleanProperty(isActive);
    }

    public String getStandardDeviation() {
        return standardDeviation.get();
    }

    public void setStandardDeviation(String standardDeviation) {
        this.standardDeviation.set(standardDeviation);
    }

    public StringProperty standardDeviationProperty() {
        return standardDeviation;
    }

    public StatisticName getStatisticName() {
        return statisticName.get();
    }

    public void setStatisticName(StatisticName statisticName) {
        this.statisticName.set(statisticName);
    }

    public ObjectProperty<StatisticName> statisticNameProperty() {
        return statisticName;
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

    public String getExpectedValue() {
        return expectedValue.get();
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue.set(expectedValue);
    }

    public StringProperty expectedValueProperty() {
        return expectedValue;
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
