package music.analysis;

import genetic.fitness.type.Fitness;
import org.testng.annotations.Test;

import java.net.URL;

public class ReaderTest {

    @Test
    public void testAnalyseMidiFile() throws Exception {
//        String midiTestFile = "simple_melody.mid";
        String midiTestFile = "yesterday.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        Reader reader = new Reader();
        Fitness fitness = reader.analyseMidiFile(filePath.getPath());

        System.out.println(fitness.getReport());
    }
}