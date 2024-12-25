package com.auth.Authentication.dto;

import java.time.LocalDate;

public class AthleteRequest {
    private String name;
    private LocalDate dob;
    private String gender;
    private Double height;
    private Double weight;
    private String category;
    private String coach;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }
}

