package genetic.representation;

public class TowseyResult {
    private double dissoncanceFitness;
    private double pitchRangeFitness;
    private double pitchVarietyFitness;
    private double rhythmicRangeFitness;
    private double RhythmVarietyFitness;

    public double getRhythmVarietyFitness() {
        return RhythmVarietyFitness;
    }

    public void setRhythmVarietyFitness(double rhythmVarietyFitness) {
        RhythmVarietyFitness = rhythmVarietyFitness;
    }

    public double getRhythmicRangeFitness() {
        return rhythmicRangeFitness;
    }

    public void setRhythmicRangeFitness(double rhythmicRangeFitness) {
        this.rhythmicRangeFitness = rhythmicRangeFitness;
    }

    public double getPitchVarietyFitness() {
        return pitchVarietyFitness;
    }

    public void setPitchVarietyFitness(double pitchVarietyFitness) {
        this.pitchVarietyFitness = pitchVarietyFitness;
    }

    public double getPitchRangeFitness() {
        return pitchRangeFitness;
    }

    public void setPitchRangeFitness(double pitchRangeFitness) {
        this.pitchRangeFitness = pitchRangeFitness;
    }

    public double getDissoncanceFitness() {
        return dissoncanceFitness;
    }

    public void setDissoncanceFitness(double dissoncanceFitness) {
        this.dissoncanceFitness = dissoncanceFitness;
    }
}
