package com.bazan.hospital.patients;

public enum Sex {
    MALE(1),
    FEMALE(2);

    private final int value;

    Sex(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Sex fromValue(int value) {
        for (Sex sex : Sex.values()) {
            if (sex.getValue() == value) {
                return sex;
            }
        }
        throw new IllegalArgumentException("Invalid value for Status: " + value);
    }
}
