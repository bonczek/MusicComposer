package genetic.fitness.type;

public class SimpleReward extends Fitness {

    @Override
    public String getReport() {
        return String.format("Simple reward: %d", fitnessValue);
    }

    public void addReward(int reward) {
        fitnessValue += reward;
    }
}
