package music.analysis.feature;

import music.notes.Note;

/**
 * This is a counter which process given note in melody line and looking for specified feature.
 * After processed melody line, counter should be cleared.
 *
 * @param <T> type of numeric result
 */
public interface MelodicFeatureCounter<T extends Number> {

    /**
     * According to given note, change state of feature counter or not.
     *
     * @param note single note from melody line
     */
    void processNote(Note note);

    /**
     * Clear remembered result to process another melody line.
     */
    void clear();

    /**
     * Return actual result of feature counter.
     *
     * @return numeric type of result
     */
    T getResult();
}
