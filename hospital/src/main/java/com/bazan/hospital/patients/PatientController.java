package com.bazan.hospital.patients;

import com.bazan.hospital.patients.DTOS.CreatePatientRequest;
import com.bazan.hospital.patients.DTOS.CreatePatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    @GetMapping("{id}")
    public ResponseEntity<CreatePatientResponse> getById(@PathVariable("id") long id) {
        try {
            var patient = patientService.getById(id);
            var response = CreatePatientResponse.Success(patient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreatePatientResponse> create(
            @RequestBody CreatePatientRequest request
    ) {
        try {
            var patient = patientService.create(request);
            var response = CreatePatientResponse.Success(patient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = CreatePatientResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }
}
