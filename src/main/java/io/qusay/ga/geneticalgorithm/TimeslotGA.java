package io.qusay.ga.geneticalgorithm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeslotGA implements Serializable {
    // Database ID
    long id;

    // M-Su, 0-6
    int day;
    // Time slots, integer by the hour. Usually 9-19 (9:00 AM - 7:00 PM)
    int time;

    // Stores set of preferences for each lecturer for this timeslotGA
    // Fitness can then look up the score for a moduleGA's lecturer to teach in this timeslotGA
    Map<Long, Integer> lecturerPreferences;

    public TimeslotGA(long id, int day, int time, HashMap<Long, Integer> lecturerPreferences) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.lecturerPreferences = lecturerPreferences;
    }

    @Override
    public String toString() {
        return "Time{" + id + " " +
                day + ":" + time +
                (lecturerPreferences.size() != 0 ? " lecPrefs=[" + lecturerPreferences.keySet().stream().map(key -> key + "=" + lecturerPreferences.get(key)).collect(Collectors.joining(",")) : "") +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Map<Long, Integer> getLecturerPreferences() {
        return lecturerPreferences;
    }

    public void setLecturerPreferences(Map<Long, Integer> lecturerPreferences) {
        this.lecturerPreferences = lecturerPreferences;
    }
}
