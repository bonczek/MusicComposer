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
        Chromosome mutatingChromosome = new Chromosome(chromosome.getPart(0, chromosome.getSize()));
        int geneIndex = randomGenerator.nextInt(chromosome.getSize());

        for (; geneIndex < mutatingChromosome.getSize(); geneIndex++) {
            if (randomGenerator.nextBoolean()) {
                if (mutatingChromosome.getGene(geneIndex).getValue() > 0) {
                    if (randomGenerator.nextBoolean()) {
                        mutatingChromosome.getGene(geneIndex).setValue(Constants.REST.value());
                    } else {
                        mutatingChromosome.getGene(geneIndex).setValue(Constants.TENUTO.value());
                    }
                    break;
                }
            } else {
                if (mutatingChromosome.getGene(geneIndex).getValue() < 0) {
                    int usedPitchIndex = randomGenerator.nextInt(mutatingChromosome.getSize());
                    for (; usedPitchIndex < mutatingChromosome.getSize(); usedPitchIndex++) {
                        if (mutatingChromosome.getGene(usedPitchIndex).getValue() > 0) {
                            mutatingChromosome.getGene(geneIndex).setValue(mutatingChromosome.getGene(usedPitchIndex).getValue().intValue());
                            break;
                        }
                    }
                }
            }
        }
        return mutatingChromosome;
    }
}
