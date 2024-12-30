package com.bazan.hospital.appointments.DTOs;

import com.bazan.hospital.appointments.Appointment;
import com.bazan.hospital.shared.DTOs.Response;

public class CreateAppointmentResponse extends Response<Appointment> {

    public CreateAppointmentResponse(boolean success, String message, Appointment data) {
        super(success, message, data);
    }

    public static CreateAppointmentResponse Success(Appointment data) {
        return new CreateAppointmentResponse(true, "Appointment Created Successfully", data);
    }

    public static CreateAppointmentResponse Failure(String message) {
        return new CreateAppointmentResponse(false, message, null);
    }
}
