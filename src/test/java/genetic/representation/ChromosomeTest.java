package genetic.representation;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class ChromosomeTest {

    private static final Integer[] values = {1, 2, 3, 4};

    private static final List<Integer> valuesList = Arrays.asList(values);

    @Test
    public void testGetGene() throws Exception {
        List<Gene> genes = valuesList.stream().map(Gene::new).collect(Collectors.toList());
        Chromosome chromosome = new Chromosome(genes);

        for (int i = 0; i < values.length; i++) {
            assertThat(chromosome.getGene(i).equals(genes.get(i)), is(true));
        }
        assertThat(genes.get(0) != chromosome.getGene(0), is(true));
    }

    @Test
    public void testGetPart() throws Exception {
        List<Gene> genes = valuesList.stream().map(Gene::new).collect(Collectors.toList());
        Chromosome chromosome = new Chromosome(genes);

        List<Gene> part = chromosome.getPart(0, 3);
        List<Integer> partValues = part.stream().map(Gene::getValue).collect(Collectors.toList());
        int changedValue = 23;
        part.get(0).setValue(changedValue);

        assertThat(partValues, contains(1, 2, 3));
        assertThat(genes.get(0) != part.get(0), is(true));
        assertThat(part.get(0).getValue(), is(changedValue));
        assertThat(genes.get(0).getValue(), is(1));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testSetPart_givenIndexOutOfRange() throws Exception {
        List<Gene> genes = valuesList.stream().map(Gene::new).collect(Collectors.toList());
        Chromosome chromosome = new Chromosome(genes);

        chromosome.setPart(new ArrayList<>(), 0, 12);
    }

}