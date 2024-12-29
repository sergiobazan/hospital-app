package com.bazan.hospital.appointments;

public enum AppointmentStatus {
    PENDING(1),
    CONFIRMED(2),
    COMPLETED(3),
    CANCELED(4);

    private final int value;

    AppointmentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AppointmentStatus fromValue(int value) {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value for Status: " + value);
    }
}
