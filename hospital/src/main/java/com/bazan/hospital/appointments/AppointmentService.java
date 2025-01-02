package com.bazan.hospital.appointments;

import com.bazan.hospital.appointments.DTOs.CreateAppointmentRequest;
import com.bazan.hospital.doctors.Doctor;
import com.bazan.hospital.doctors.IDoctorRepository;
import com.bazan.hospital.patients.IPatientRepository;
import com.bazan.hospital.patients.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository appointmentRepository;
    private final IDoctorRepository doctorRepository;
    private final IPatientRepository patientRepository;

    @Override
    public Appointment getById(long id) throws Exception {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Appointment not found"));
    }

    @Override
    public Appointment create(CreateAppointmentRequest appointmentRequest) throws Exception {
        var doctor = doctorRepository
                .findById(appointmentRequest.doctorId())
                .orElseThrow(() -> new Exception("Doctor not found"));

        var patient = patientRepository
                .findById(appointmentRequest.patientId())
                .orElseThrow(() -> new Exception("Patient not found"));

        if (appointmentRepository.isOverlapping(doctor.getId(), appointmentRequest.start(), appointmentRequest.finish())) {
            throw new Exception("The doctor already has an appointment during this time.");
        }

        var appointment = Appointment.Create(
                appointmentRequest.title(),
                appointmentRequest.start(),
                appointmentRequest.finish(),
                doctor,
                patient
        );

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
