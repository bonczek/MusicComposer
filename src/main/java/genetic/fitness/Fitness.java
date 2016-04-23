package genetic.fitness;

public abstract class Fitness {

    protected Integer fitnessValue = 0;

    public Integer getFitnessValue() {
        return fitnessValue;
    }

    public abstract String getReport();
}
