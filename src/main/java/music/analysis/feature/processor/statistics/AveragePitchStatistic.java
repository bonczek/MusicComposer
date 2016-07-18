package music.analysis.feature.processor.statistics;

import music.notes.Note;
import music.notes.Sound;
import music.notes.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;

public class AveragePitchStatistic extends StatisticCounter<Double> {

    private List<Integer> midiValueList = new ArrayList<>();

    public AveragePitchStatistic() {
        super(0.0, (double) Pitch.MAX_MIDI_VALUE);
    }

    @Override
    public void processNote(Note note) {
        if (note instanceof Sound) {
            Sound sound = (Sound) note;
            midiValueList.add(sound.getPitch().getMidiValue());
            numerator = (double) midiValueList.stream().mapToInt(i -> i).sum() / midiValueList.size();
        }
    }

    @Override
    public void clear() {
        numerator = 0.0;
        midiValueList.clear();
    }

    public int getMidiValueListSize() {
        return midiValueList.size();
    }
}
