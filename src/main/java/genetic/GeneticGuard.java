package genetic;

import java.util.Random;

/**
 * Created by adam on 09.01.16.
 */
public class GeneticGuard {

    private final double operationRate;

    private final Random numberGenerator;

    public GeneticGuard(double operationRate) {
        this.operationRate = operationRate;
        numberGenerator = new Random();
    }

    public boolean permitOperation() {
        double randomNumber = numberGenerator.nextDouble();
        if (Double.compare(operationRate, randomNumber) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
