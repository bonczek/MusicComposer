package genetic.fitness;

import genetic.representation.Chromosome;

import java.util.List;

/**
 * Created by adam on 10.01.16.
 */
public class SimpleCMajorFitness implements FitnessFunction {

    @Override
    public void calculateFitness(List<Chromosome> population) {
        population.forEach(c -> c.setFitness(rateChromosome(c)));
    }

    public Integer rateChromosome(Chromosome chromosome) {
        return chromosome.getGenesValues().stream().mapToInt(this::rateNote).sum();
    }

    private Integer rateNote(int noteValue) {
        //MAJOR_SCALE = {0, 2, 4, 5, 7, 9, 11}
        if (noteValue == -1 || noteValue == -2) {
            return 5;
        }
        int scaleValue = noteValue % 12;
        if (scaleValue == 0 || scaleValue == 4 || scaleValue == 7) {
            return 20;
        } else if (scaleValue == 2 || scaleValue == 5 || scaleValue == 9 || scaleValue == 11) {
            return 10;
        } else {
            return 0;
        }
    }

}
