package genetic;

import genetic.representation.Chromosome;

import java.util.List;

/**
 * Created by adam on 20.01.16.
 */
public class Niche {

    public void decreaseFitnessValueForSimilar(List<Chromosome> population) {
        for (Chromosome c : population) {
            float decreaseNumber = 1.0f;
            for (Chromosome other : population) {
                for (int i = 0; i < c.getSize(); i++) {
                    if (c.getGene(i).equals(other.getGene(i))) {
                        decreaseNumber += 0.1;
                    }
                }
            }
            float newFitness = c.getFitness() / decreaseNumber;
            c.setFitness(Math.round(newFitness));
        }
    }
}
