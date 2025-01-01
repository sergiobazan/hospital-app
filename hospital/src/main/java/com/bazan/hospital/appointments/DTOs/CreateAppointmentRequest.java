package com.bazan.hospital.appointments.DTOs;

import java.time.LocalDateTime;

public record CreateAppointmentRequest(String title, LocalDateTime start, LocalDateTime finish, long doctorId, long patientId) {
}
