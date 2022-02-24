package io.qusay.ga.geneticalgorithm;

import java.io.Serializable;

/**
 * A "gene", or a list of modules and the timeslotGA/venueGA they have been scheduled into
 */
public class Gene implements Cloneable, Serializable {
    private GeneticAlgorithmJobData data;

    ModuleGA moduleGA;
    VenueGA venueGA;
    TimeslotGA timeslotGA;

    @Override
    public String toString() {
        return "Gene{" + moduleGA + "," + venueGA + "," + timeslotGA + '}';
    }

    /**
     * Randomising constructor
     */
    public Gene(ModuleGA moduleGA, GeneticAlgorithmJobData masterData) {
        data = masterData;

        this.moduleGA = moduleGA;
        this.venueGA = data.getRandomVenue();
        this.timeslotGA = data.getRandomTimeslot();
    }

    /**
     * Cloning constructor
     */
    public Gene(ModuleGA moduleGA, VenueGA venueGA, TimeslotGA timeslotGA, GeneticAlgorithmJobData masterData) {
        data = masterData;

        this.moduleGA = moduleGA;
        this.venueGA = venueGA;
        this.timeslotGA = timeslotGA;
    }

    public Gene clone() {
        return new Gene(this.moduleGA, this.venueGA, this.timeslotGA, this.data);
    }

    /**
     * Compare two genes. If they overlap in both venueGA and timeslotGA, there is a conflict! Return true
     * If they overlap in JUST timeslotGA but the two modules are offered BY THE SAME COURSE, return true
     * If they overlap in JUST timeslotGA and the two modules are taught by the same lecturer, return true
     *
     * @return true if these two modules should not both be taught in the same timeslotGA. False means no conflicts
     */
    public boolean conflictsWithTimeOrPlaceOrLecturerOf(Gene that) {
        if (this == that) return true;

        if (timeslotGA == that.timeslotGA) {
            // Check if scheduled at same time AND place
            if (venueGA == that.venueGA) {
                return true;
            }

            // Check if the lecturer is already busy this hour
            if (moduleGA.taughtByTheSameLecturer(that.moduleGA)) {
                return true;
            }

            // Check if a time conflict is also for two modules within the same course. If not, the overlap doesn't matter
            // Done last, since it is most likely the worst performance test
            if (moduleGA.offeredBySameCourse(that.moduleGA)) {
                return true;
            }
        }

        return false; // No conflict!
    }

    /**
     * For this gene, has it been assigned to a lecture/lab venueGA that is proper?
     *
     * @return true if the moduleGA can be successfully taught within this venueGA
     */
    public boolean isInValidVenue() {
        if (this.moduleGA.isLab != this.venueGA.isLab) return false;
        if (this.venueGA.capacity < this.moduleGA.numEnrolled) return false;
        return true;
    }

    public static final int MAX_BUILDING_PREF_SCORE = 20;
    public float getDepartmentsBuildingPreferenceAverage() {
        int scoreSum = 0;
        for (Long departamentId : moduleGA.getDepartmentIds()) {
            scoreSum += venueGA.getDepartmentsScores().getOrDefault(departamentId, 10); // If the key does not exist, then count it as 10, or "half"
        }
        float avg = (float) scoreSum / moduleGA.getDepartmentIds().size();
        if (avg > 20) {
            System.out.println("An average score was over 20: moduleGA=" + moduleGA.getId() + ",venueGA=" + venueGA.getId());
        }
        return avg;
    }

    public static final int MAX_TIMESLOT_PREF_SCORE = 20;
    public int getLecturerTimeslotPreference() {
        return timeslotGA.getLecturerPreferences().getOrDefault(moduleGA.lecturerId, 10); // If the database field of this lecturer in this timeslotGA does not exist, then count it as 10, or "half"
    }

    public ModuleGA getModule() {
        return moduleGA;
    }

    public void setModule(ModuleGA moduleGA) {
        this.moduleGA = moduleGA;
    }

    public VenueGA getVenue() {
        return venueGA;
    }

    public void setVenue(VenueGA venueGA) {
        this.venueGA = venueGA;
    }

    public TimeslotGA getTimeslot() {
        return timeslotGA;
    }

    public void setTimeslot(TimeslotGA timeslotGA) {
        this.timeslotGA = timeslotGA;
    }
}
