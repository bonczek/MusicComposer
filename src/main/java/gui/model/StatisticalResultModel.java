package gui.model;

import javafx.beans.property.SimpleStringProperty;

public class StatisticalResultModel {

    private SimpleStringProperty statisticName;
    private SimpleStringProperty result;
    private SimpleStringProperty expectedValue;
    private SimpleStringProperty difference;
    private SimpleStringProperty weight;
    private SimpleStringProperty standardDeviation;
    private SimpleStringProperty reward;

    public StatisticalResultModel(String statisticName, String result, String
            expectedValue, String difference, String weight, String standardDeviation,
                                  String reward) {
        this.statisticName = new SimpleStringProperty(statisticName);
        this.result = new SimpleStringProperty(result);
        this.expectedValue = new SimpleStringProperty(expectedValue);
        this.difference = new SimpleStringProperty(difference);
        this.weight = new SimpleStringProperty(weight);
        this.standardDeviation = new SimpleStringProperty(standardDeviation);
        this.reward = new SimpleStringProperty(reward);
    }

    public StatisticalResultModel(StatisticalResult statisticalResult) {
        this.statisticName = new SimpleStringProperty(statisticalResult.getStatisticName());
        this.result = new SimpleStringProperty(Double.toString(statisticalResult.getResult()));
        this.expectedValue = new SimpleStringProperty(Double.toString(statisticalResult.getExpectedValue()));
        this.difference = new SimpleStringProperty(Double.toString(statisticalResult.getDifference()));
        this.weight = new SimpleStringProperty(Double.toString(statisticalResult.getWeight()));
        this.standardDeviation = new SimpleStringProperty(Double.toString(statisticalResult.getStandardDeviation()));
        this.reward = new SimpleStringProperty(Integer.toString(statisticalResult.getReward()));
    }

    public String getDifference() {
        return difference.get();
    }

    public void setDifference(String difference) {
        this.difference.set(difference);
    }

    public SimpleStringProperty differenceProperty() {
        return difference;
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }

    public String getStatisticName() {
        return statisticName.get();
    }

    public void setStatisticName(String statisticName) {
        this.statisticName.set(statisticName);
    }

    public SimpleStringProperty statisticNameProperty() {
        return statisticName;
    }

    public String getExpectedValue() {
        return expectedValue.get();
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue.set(expectedValue);
    }

    public SimpleStringProperty expectedValueProperty() {
        return expectedValue;
    }

    public String getStandardDeviation() {
        return standardDeviation.get();
    }

    public void setStandardDeviation(String standardDeviation) {
        this.standardDeviation.set(standardDeviation);
    }

    public SimpleStringProperty standardDeviationProperty() {
        return standardDeviation;
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public String getReward() {
        return reward.get();
    }

    public void setReward(String reward) {
        this.reward.set(reward);
    }

    public SimpleStringProperty rewardProperty() {
        return reward;
    }
}
