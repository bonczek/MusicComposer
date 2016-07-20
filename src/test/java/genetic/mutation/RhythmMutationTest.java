package genetic.mutation;

import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.representation.Constants;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class RhythmMutationTest {

    private Random randomMock = Mockito.mock(Random.class);
    private RhythmMutation rhythmMutation = new RhythmMutation(randomMock, InitialPopulationGenerator.NOTES_IN_MEASURE);

    @Test
    public void testMutate_givenLargeRhythmicValuesChosen() throws Exception {
        int halfNoteLength = 8;
        int mutationIndex = 32;
        Chromosome chromosome = ChromosomeData.prepareFourMeasuresChromosome();

        when(randomMock.nextBoolean()).thenReturn(true);
        when(randomMock.nextInt(InitialPopulationGenerator.NOTES_IN_MEASURE)).thenReturn(halfNoteLength);
        when(randomMock.nextInt(chromosome.getSize() - halfNoteLength)).thenReturn(mutationIndex);

        Chromosome mutated = rhythmMutation.mutate(chromosome);
        Chromosome beforeMutation = ChromosomeData.prepareFourMeasuresChromosome();
        for (int i = 0; i < mutated.getSize(); i++) {
            if (i >= mutationIndex && i < mutationIndex + halfNoteLength) {
                assertThat(mutated.getGene(i).getValue(), is(new Integer(Constants.TENUTO.value()).shortValue()));
            } else {
                assertThat(mutated.getGene(i).equals(beforeMutation.getGene(i)), is(true));
            }
        }
    }

    @Test
    public void testMutate_givenLargeRhythmicValuesChosenWithMaxLengthAndIndex() throws Exception {
        int wholeNoteLength = 16;
        int mutationIndex = 47;
        Chromosome chromosome = ChromosomeData.prepareFourMeasuresChromosome();

        when(randomMock.nextBoolean()).thenReturn(true);
        when(randomMock.nextInt(InitialPopulationGenerator.NOTES_IN_MEASURE)).thenReturn(wholeNoteLength);
        when(randomMock.nextInt(chromosome.getSize() - wholeNoteLength)).thenReturn(mutationIndex);

        Chromosome mutated = rhythmMutation.mutate(chromosome);
        Chromosome beforeMutation = ChromosomeData.prepareFourMeasuresChromosome();
        for (int i = 0; i < mutated.getSize(); i++) {
            if (i >= mutationIndex && i < mutationIndex + wholeNoteLength) {
                assertThat(mutated.getGene(i).getValue(), is(new Integer(Constants.TENUTO.value()).shortValue()));
            } else {
                assertThat(mutated.getGene(i).equals(beforeMutation.getGene(i)), is(true));
            }
        }
    }

    @Test
    public void testMutate_givenNewNoteIsChosen() throws Exception {
        int mutationIndex = 4;
        int midi = 50;

        Chromosome chromosome = ChromosomeData.prepareFourMeasuresChromosome();

        when(randomMock.nextBoolean()).thenReturn(false);
        when(randomMock.nextInt(chromosome.getSize())).thenReturn(mutationIndex);
        when(randomMock.nextInt(RhythmMutation.NUMBER_OF_MIDI_VALUES)).thenReturn(midi);

        Chromosome mutated = rhythmMutation.mutate(chromosome);
        Chromosome beforeMutation = ChromosomeData.prepareFourMeasuresChromosome();
        for (int i = 0; i < mutated.getSize(); i++) {
            if (i == mutationIndex) {
                assertThat(mutated.getGene(i).getValue(), is((short) midi));
            } else {
                assertThat(mutated.getGene(i).equals(beforeMutation.getGene(i)), is(true));
            }
        }
    }
}