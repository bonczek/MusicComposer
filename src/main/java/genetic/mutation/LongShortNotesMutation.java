package genetic.mutation;

import genetic.representation.Chromosome;
import genetic.representation.Constants;
import genetic.util.Converter;
import jm.constants.Durations;
import music.notes.Note;
import music.notes.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LongShortNotesMutation extends GeneticMutation {

    static final int SHORT_NOTES_EXTENDING_LIMIT = 4;

    public LongShortNotesMutation(Random randomGenerator) {
        super(randomGenerator);
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        Map<Integer, Note> noteIndexMap = Converter.fromChromosomeWithIndexes(chromosome);
        List<Integer> noteStartIndexes = new ArrayList<>(noteIndexMap.keySet());
        int noteIndex = randomGenerator.nextInt(noteStartIndexes.size());
        Note chosenNote = noteIndexMap.get(noteStartIndexes.get(noteIndex));

        if (Double.compare(chosenNote.getRhythmValue(), Durations.QUARTER_NOTE) > 0) {
            splitLongNoteRhythmicValue(noteIndex, chosenNote, chromosome);
        } else if (Double.compare(chosenNote.getRhythmValue(), Durations.QUARTER_NOTE) < 0) {
            extendShortNoteRhythmicValue(noteIndex, chosenNote, chromosome);
        }
        return chromosome;
    }

    private void splitLongNoteRhythmicValue(int noteStartIndex, Note longNote, Chromosome chromosome) {
        //!Warning - rhythm should be multiplication of default rhythmic value
        int numberOfTenutoGenes = (int) (longNote.getRhythmValue() / Converter.DEFAULT_RHYTHMIC_VALUE) - 1;
        int firstTenutoIndex = 1 + noteStartIndex;
        int newNoteIndex = randomGenerator.nextInt(numberOfTenutoGenes) + firstTenutoIndex;
        int newGeneValue;
        if (longNote instanceof Sound) {
            Sound sound = (Sound) longNote;
            newGeneValue = sound.getPitch().getMidiValue();
        } else {
            newGeneValue = Constants.REST.value();
        }
        chromosome.getGene(newNoteIndex).setValue(newGeneValue);
    }

    private void extendShortNoteRhythmicValue(int noteStartIndex, Note shortNote, Chromosome chromosome) {
        int numberOfNoteGenes = (int) (shortNote.getRhythmValue() / Converter.DEFAULT_RHYTHMIC_VALUE);
        int numberOfExtensionGenes = randomGenerator.nextInt(SHORT_NOTES_EXTENDING_LIMIT) + 1;
        int extendedNumberOfGenes = numberOfNoteGenes + numberOfExtensionGenes;
        for (int i = noteStartIndex + numberOfNoteGenes; i < noteStartIndex + extendedNumberOfGenes; i++) {
            if (i < chromosome.getSize()) {
                chromosome.getGene(i).setValue(Constants.TENUTO.value());
            }
        }
    }
}
