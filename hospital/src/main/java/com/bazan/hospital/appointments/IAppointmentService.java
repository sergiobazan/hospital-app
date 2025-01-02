package com.bazan.hospital.appointments;

import com.bazan.hospital.appointments.DTOs.AppointmentDoctorPatientResponse;
import com.bazan.hospital.appointments.DTOs.CreateAppointmentRequest;
import com.bazan.hospital.doctors.Doctor;
import com.bazan.hospital.patients.Patient;

import java.util.List;

public interface IAppointmentService {
    Appointment getById(long id) throws Exception;
    Appointment create(CreateAppointmentRequest appointmentRequest) throws Exception;
    List<Appointment> getAll();
    List<Doctor> getAllDoctors();
    List<Patient> getAllPatients();
    AppointmentDoctorPatientResponse getDoctorAndPatientById(long id) throws Exception;
}
