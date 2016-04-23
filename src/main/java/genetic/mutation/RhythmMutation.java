package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;

import java.util.Random;

public class RhythmMutation extends GeneticMutation {

    public RhythmMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        int geneIndex = randomGenerator.nextInt(chromosome.getSize());

        for (; geneIndex < chromosome.getSize(); geneIndex++) {
            if (randomGenerator.nextBoolean()) {
                if (chromosome.getGene(geneIndex).getValue() > 0) {
                    if (randomGenerator.nextBoolean()) {
                        chromosome.getGene(geneIndex).setValue(Constants.REST.value());
                    } else {
                        chromosome.getGene(geneIndex).setValue(Constants.TENUTO.value());
                    }
                    break;
                }
            } else {
                if (chromosome.getGene(geneIndex).getValue() < 0) {
                    int usedPitchIndex = randomGenerator.nextInt(chromosome.getSize());
                    for (; usedPitchIndex < chromosome.getSize(); usedPitchIndex++) {
                        if (chromosome.getGene(usedPitchIndex).getValue() > 0) {
                            chromosome.getGene(geneIndex).setValue(chromosome.getGene(usedPitchIndex).getValue().intValue());
                            break;
                        }
                    }
                }
            }
        }
        return chromosome;
    }
}
