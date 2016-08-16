package music.analysis;

import org.testng.annotations.Test;

import java.net.URL;

public class ReaderTest {

    @Test
    public void testAnalyseMidiFile() throws Exception {
        String midiTestFile = "yesterday.mid";
        URL filePath = getClass().getClassLoader().getResource(midiTestFile);

        Reader reader = new Reader();
        String report = reader.analyseMidiFile(filePath.getPath());

        System.out.println(report);
    }
}