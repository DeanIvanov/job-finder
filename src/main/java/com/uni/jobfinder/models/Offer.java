package com.uni.jobfinder.models;

import lombok.*;

@Builder
public class Offer {

    String ref;
    String link;
    String description;
    String positionName;
    String location;
    String salary;

    public Offer() {
    }

    public Offer(String ref, String link, String description, String positionName, String location, String salary) {
        this.ref = ref;
        this.link = link;
        this.description = description;
        this.positionName = positionName;
        this.location = location;
        this.salary = salary;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    // override toString method for better data output
    @Override
    public String toString() {
        return  "\n" +
                "Reference number: " + getRef() + "\n" +
                "Link: " + getLink() + "\n" +
                "Offer title: " + getDescription() + "\n" +
                "Position: " + getPositionName() + "\n" +
                "Location: " + getLocation() + "\n" +
                "Salary: " + getSalary() + "\n";
    }
}
