package com.hrsystem.hrsystem.entity.enums;

public enum Degree {
    Fresh, Intermediate , Senioir , Architect;

    public static Degree getDegree(String name) {
        if (name.endsWith("h")) return Fresh ;
        else if (name.endsWith("e")) return Intermediate;
        else if (name.endsWith("r")) return Senioir ;
        else return Architect;
    }
}
