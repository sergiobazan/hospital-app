package com.bazan.hospital.appointments.DTOs;

import java.time.LocalDateTime;

public record CreateAppointmentRequest(LocalDateTime date, long doctorId, long patientId) {
}
