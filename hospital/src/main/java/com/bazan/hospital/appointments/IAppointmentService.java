package com.bazan.hospital.appointments;

import com.bazan.hospital.appointments.DTOs.CreateAppointmentRequest;

public interface IAppointmentService {
    Appointment getById(long id) throws Exception;
    Appointment create(CreateAppointmentRequest appointmentRequest) throws Exception;
}
