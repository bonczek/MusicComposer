package music.harmony;

import jm.constants.Durations;

import java.util.List;

public class ChordProgressionParser {


    public List<Chord> parseProgressionText(String progressionText, int numberOfMeasures) throws
            IllegalArgumentException {
        //@todo allow more chords in one measure
        ChordProgressionBuilder progressionBuilder = new ChordProgressionBuilder();
        String[] measureStrings = progressionText.split("\\|");
        try {
            for (int i = 0; i < numberOfMeasures; i++) {
                int j = i % measureStrings.length;
                ChordText parsedChord = ChordText.valueOf(measureStrings[j]);
                progressionBuilder.appendChord(parsedChord.getChordHarmony(), Durations.WHOLE_NOTE);
            }
            return progressionBuilder.getChordList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Failed to read given chord sequence: %s. Invalid " +
                    "format", progressionText), e);
        }
    }
}
