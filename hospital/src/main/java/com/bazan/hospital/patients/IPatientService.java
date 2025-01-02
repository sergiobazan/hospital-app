package com.bazan.hospital.patients;

import com.bazan.hospital.patients.DTOS.CreatePatientRequest;

import java.util.List;

public interface IPatientService {
    Patient getById(long id) throws Exception;
    Patient create(CreatePatientRequest patientRequest) throws Exception;
    List<Patient> getAll();
}
