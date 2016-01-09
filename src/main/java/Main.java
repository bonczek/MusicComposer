import inst.SawtoothInst;
import jm.JMC;
import jm.audio.Instrument;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;

/**
 * Created by adam on 31.12.15.
 */
public class Main implements JMC {

    public static void main(String[] args) {
        Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
        Score s = prepareTestMelody();
        Part p = new Part();
        Phrase ph = new Phrase();
        //Read.xml(ph, "src/main/resources/example.xml");


        //Read.midi(s);
//        Note[] n = s.getPart(0).getPhrase(0).getNoteArray();
//
        //Write.jm(score, "untitled.jm");
        Instrument inst = new SawtoothInst(44100);
        Instrument[] instruments = {new SawtoothInst(44100)};

        Play.audio(s, instruments);
//        Write.xml(s);
//        Write.midi(s);
    }

    private static Score prepareTestMelody() {
        Score score = new Score();
        Part part = new Part();
        Phrase phrase = new Phrase();
        phrase.setTempo(120.0);

        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(G5, HALF_NOTE));

        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(C5, HALF_NOTE));

        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, HALF_NOTE));

        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, HALF_NOTE));

        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(C5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(G5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(A5, QUARTER_NOTE));
        phrase.add(new Note(G5, HALF_NOTE));

        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(F5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(E5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(D5, QUARTER_NOTE));
        phrase.add(new Note(C5, HALF_NOTE));

        part.add(phrase);
        score.add(part);
        return score;
    }
}
