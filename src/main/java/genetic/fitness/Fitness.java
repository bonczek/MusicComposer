package genetic.fitness;

public class Fitness {

    protected Integer fitnessValue = 0;

    public Integer getFitnessValue() {
        return fitnessValue;
    }

    public void clearFitnessValue() {
        fitnessValue = 0;
    }

    public void addReward(int reward) {
        fitnessValue += reward;
    }
}
