package io.qusay.ga.geneticalgorithm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VenueGA implements Serializable {
    // Database ID
    long id;

    // DEBUG: Name is just for debugging
    String name;

    // Is a computer lab. If false, this is a lecture venueGA
    boolean isLab;

    // Seats for students
    int capacity;

    // Location of the building which this venueGA is in
    double locationX;
    double locationY;

    // The score the all departments have given this venueGA
    // When a moduleGA has been tentatively scheduled in this venueGA, can look up all departments teaching that moduleGA here
    // and then average the scores for fitness
    Map<Long, Integer> departmentsScores;

    public VenueGA(long id, String name, boolean isLab, int capacity, double locationX, double locationY, HashMap<Long, Integer> departmentsScores) {
        this.id = id;
        this.name = name;
        this.isLab = isLab;
        this.capacity = capacity;
        this.locationX = locationX;
        this.locationY = locationY;
        this.departmentsScores = departmentsScores;
    }

    @Override
    public String toString() {
        return "VenueGA{" + id + " " +
                name +
                " size=" + capacity +
                " lab=" + isLab +
                " deptScores=[" + departmentsScores.keySet().stream().map(key -> key + "=" + departmentsScores.get(key)).collect(Collectors.joining(",")) +
                "]}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLab() {
        return isLab;
    }

    public void setLab(boolean lab) {
        isLab = lab;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public Map<Long, Integer> getDepartmentsScores() {
        return departmentsScores;
    }

    public void setDepartmentsScore(Map<Long, Integer> departmentsScores) {
        this.departmentsScores = departmentsScores;
    }
}
