package music.harmony;

import music.harmony.chords.Chord;
import music.harmony.chords.ChordProgressionParser;
import music.harmony.chords.ChordText;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChordProgressionParserTest {

    @Test
    public void testParseChordProgressionString() throws Exception {
        ChordProgressionParser parser = new ChordProgressionParser();
        String exampleString = "G|C|D|C";
        int numberOfMeasures = 4;

        List<Chord> chordList = parser.parseProgressionText(exampleString, numberOfMeasures);

        assertThat(chordList.size(), is(numberOfMeasures));
        assertThat(chordList.get(0).getChordHarmony(), is(ChordText.G.getChordHarmony()));
        assertThat(chordList.get(1).getChordHarmony(), is(ChordText.C.getChordHarmony()));
        assertThat(chordList.get(2).getChordHarmony(), is(ChordText.D.getChordHarmony()));
        assertThat(chordList.get(3).getChordHarmony(), is(ChordText.C.getChordHarmony()));
    }

    @Test
    public void testParseChordProgressionString_givenMoreMeasuresThanChords() throws Exception {
        ChordProgressionParser parser = new ChordProgressionParser();
        String exampleString = "G|C|D|C";
        int numberOfMeasures = 10;

        List<Chord> chordList = parser.parseProgressionText(exampleString, numberOfMeasures);

        assertThat(chordList.size(), is(numberOfMeasures));
        assertThat(chordList.get(0).getChordHarmony(), is(ChordText.G.getChordHarmony()));
        assertThat(chordList.get(1).getChordHarmony(), is(ChordText.C.getChordHarmony()));
        assertThat(chordList.get(6).getChordHarmony(), is(ChordText.D.getChordHarmony()));
        assertThat(chordList.get(7).getChordHarmony(), is(ChordText.C.getChordHarmony()));
        assertThat(chordList.get(8).getChordHarmony(), is(ChordText.G.getChordHarmony()));
        assertThat(chordList.get(9).getChordHarmony(), is(ChordText.C.getChordHarmony()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testParseChordProgression_givenInvalidNotation() throws Exception {
        ChordProgressionParser parser = new ChordProgressionParser();
        String exampleString = "G#|C#|Db|C#";
        int numberOfMeasures = 4;
        parser.parseProgressionText(exampleString, numberOfMeasures);
    }
}