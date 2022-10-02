package com.hrsystem.hrsystem.entity.enums;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGender(String name) {
        if (name.matches("male")||name.matches("Male") ) return MALE;
        else return FEMALE;
    }
}
