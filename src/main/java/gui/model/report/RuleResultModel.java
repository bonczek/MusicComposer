package gui.model.report;

import javafx.beans.property.SimpleStringProperty;

public class RuleResultModel {

    private SimpleStringProperty statisticName;
    private SimpleStringProperty count;
    private SimpleStringProperty weight;
    private SimpleStringProperty reward;

    public RuleResultModel(String statisticName, String count, String weight, String reward) {
        this.statisticName = new SimpleStringProperty(statisticName);
        this.count = new SimpleStringProperty(count);
        this.weight = new SimpleStringProperty(weight);
        this.reward = new SimpleStringProperty(reward);
    }

    public RuleResultModel(RuleResult ruleResult) {
        this.statisticName = new SimpleStringProperty(ruleResult.getName());
        this.count = new SimpleStringProperty(Double.toString(ruleResult.getCount()));
        this.weight = new SimpleStringProperty(Double.toString(ruleResult.getWeight()));
        this.reward = new SimpleStringProperty(Double.toString(ruleResult.getReward()));
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

    public String getStatisticName() {
        return statisticName.get();
    }

    public void setStatisticName(String statisticName) {
        this.statisticName.set(statisticName);
    }

    public SimpleStringProperty statisticNameProperty() {
        return statisticName;
    }

    public String getCount() {
        return count.get();
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public SimpleStringProperty countProperty() {
        return count;
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
}
