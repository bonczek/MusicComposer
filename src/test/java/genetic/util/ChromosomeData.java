package genetic.util;

import edu.emory.mathcs.backport.java.util.Arrays;
import genetic.representation.Chromosome;
import genetic.representation.Gene;

import java.util.List;
import java.util.stream.Collectors;

public class ChromosomeData {

    public static Chromosome prepareFourMeasuresChromosome() {
        Integer[] values = {8, 23, 23, 17, 27, 18, 94, 94, 93, 23, 93, 23, 78, 23, 78, 78, 78, 93, 78, 69, 93, 18, 27, 17, 58, 58, 18, 17, 18,
                27, 23, 93, 7, 7, 7, 7, 17, 17, 17, 94, 8, 94, 7, 69, 17, 112, 69, 17, 112, 112, 18, 7, 8, 81, 27, 8, -1, 81, 58, 94, 94, 112, 112, -1};
        return createWithIntegerValues(Arrays.asList(values));
    }

    public static Chromosome createWithIntegerValues(List<Integer> values) {
        List<Gene> genes = values.stream().map(value -> new Gene(value.shortValue())).collect(Collectors.toList());
        return new Chromosome(genes);
    }
}
