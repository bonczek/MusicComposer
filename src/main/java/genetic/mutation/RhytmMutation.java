package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class RhytmMutation extends GeneticMutation {

    private final Random numberGenerator;

    public RhytmMutation(double mutationRate, Random numberGenerator) {
        super(mutationRate);
        this.numberGenerator = numberGenerator;
    }

    @Override
    protected void mutateChromosome(Chromosome chromosome) {
        int geneIndex = numberGenerator.nextInt(chromosome.getSize());

        for (; geneIndex < chromosome.getSize(); geneIndex++) {
            if (numberGenerator.nextBoolean()) {
                if (chromosome.getGene(geneIndex).getValue() > 0) {
                    if (numberGenerator.nextBoolean()) {
                        chromosome.getGene(geneIndex).setValue(Constants.REST.value());
                    } else {
                        chromosome.getGene(geneIndex).setValue(Constants.TENUTO.value());
                    }
                    break;
                }
            } else {
                if (chromosome.getGene(geneIndex).getValue() < 0) {
                    int usedPitchIndex = numberGenerator.nextInt(chromosome.getSize());
                    for (; usedPitchIndex < chromosome.getSize(); usedPitchIndex++) {
                        if (chromosome.getGene(usedPitchIndex).getValue() > 0) {
                            chromosome.getGene(geneIndex).setValue(chromosome.getGene(usedPitchIndex).getValue().intValue());
                            break;
                        }
                    }
                }
            }
        }

    }
}
