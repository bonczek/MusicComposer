package gui.model.report;

import java.util.ArrayList;
import java.util.List;

public class StatisticalResult {
    private String statisticName;
    private double result;
    private double expectedValue;
    private double difference;
    private double weight;
    private double standardDeviation;
    private int reward;

    public StatisticalResult(String statisticName, double result, double expectedValue, double difference, double weight, double standardDeviation, int reward) {
        this.statisticName = statisticName;
        this.result = result;
        this.expectedValue = expectedValue;
        this.difference = difference;
        this.weight = weight;
        this.standardDeviation = standardDeviation;
        this.reward = reward;
    }

    /**
     * todo: method should be changed. Generating reports as a String and then read parsing it is unnecessary.
     * Parsing is used because changes in structure might cause too many modification in master thesis document.
     */
    public static List<StatisticalResult> parseReportLines(String[] reportLines) {
        List<StatisticalResult> ruleResults = new ArrayList<>();
        try {
            for (int i = 1; i < reportLines.length; i++) {
                //it depends on locale, but in case numbers are separated by comma, change it to dot
                reportLines[i] = reportLines[i].replaceAll(",", ".");
                String[] columns = reportLines[i].split(";");
                ruleResults.add(new StatisticalResult(columns[0], Double.parseDouble(columns[1]),
                        Double.parseDouble(columns[2]), Double.parseDouble(columns[3]), Double.parseDouble(columns[4]),
                        Double.parseDouble(columns[5]), Integer.parseInt(columns[6])));
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(String.format("Error during result parsing. Result table won't show. %s",
                    e.getMessage()));
        }
        return ruleResults;
    }

    public String getStatisticName() {
        return statisticName;
    }

    public void setStatisticName(String statisticName) {
        this.statisticName = statisticName;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(double expectedValue) {
        this.expectedValue = expectedValue;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
