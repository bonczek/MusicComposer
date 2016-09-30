package gui.model;

import java.util.ArrayList;
import java.util.List;

public class RuleResult {

    private String name;
    private double weight;
    private double count;
    private int reward;

//    private static final NumberFormat FORMAT = NumberFormat.getInstance(Locale.FRANCE);

    public RuleResult(String name, double count, double weight, int reward) {
        this.name = name;
        this.count = count;
        this.weight = weight;
        this.reward = reward;
    }

    /**
     * todo: method should be changed. Generating reports as a String and then read parsing it is unnecessary.
     * Parsing is used because changes in structure might cause too many modification in master thesis document.
     */
    public static List<RuleResult> parseReportLines(String[] reportLines) {
        List<RuleResult> ruleResults = new ArrayList<>();
        try {
            for (int i = 1; i < reportLines.length; i++) {
                //it depends on locale, but in case numbers are separated by comma, change it to dot
                reportLines[i] = reportLines[i].replaceAll(",", ".");
                String[] columns = reportLines[i].split(";");
                ruleResults.add(new RuleResult(columns[0], Double.parseDouble(columns[1]),
                        Double.parseDouble(columns[2]), Integer.parseInt(columns[3])));
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(String.format("Error during result parsing. Result table won't show. %s",
                    e.getMessage()));
        }
        return ruleResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
