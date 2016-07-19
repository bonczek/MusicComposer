package genetic.mutation;

import genetic.initial.InitialPopulationGenerator;
import genetic.representation.Chromosome;
import genetic.representation.Constants;
import genetic.util.ChromosomeData;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
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
    public void testMutate_givenNewNotesIsChosen() throws Exception {
        int numberOfNewValues = 4;
        Map<Integer, Integer> mutations = new HashMap<>();
        int[] indexes = {1, 2, 4, 8};
        int[] midi = {10, 30, 50, 80};
        for (int i = 0; i < 4; i++) {
            mutations.put(indexes[i], midi[i]);
        }

        Chromosome chromosome = ChromosomeData.prepareFourMeasuresChromosome();

        when(randomMock.nextBoolean()).thenReturn(false);
        when(randomMock.nextInt(RhythmMutation.MAX_NEW_SOUNDS)).thenReturn(numberOfNewValues);
        when(randomMock.nextInt(chromosome.getSize())).thenReturn(indexes[0], indexes[1], indexes[2], indexes[3]);
        when(randomMock.nextInt(RhythmMutation.NUMBER_OF_MIDI_VALUES)).thenReturn(midi[0], midi[1], midi[2], midi[3]);

        Chromosome mutated = rhythmMutation.mutate(chromosome);
        Chromosome beforeMutation = ChromosomeData.prepareFourMeasuresChromosome();
        for (int i = 0; i < mutated.getSize(); i++) {
            if (mutations.containsKey(i)) {
                assertThat(mutated.getGene(i).getValue(), is(mutations.get(i).shortValue()));
            } else {
                assertThat(mutated.getGene(i).equals(beforeMutation.getGene(i)), is(true));
            }
        }
    }
}