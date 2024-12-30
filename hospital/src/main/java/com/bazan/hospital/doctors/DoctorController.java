package com.bazan.hospital.doctors;

import com.bazan.hospital.doctors.DTOs.CreateDoctorRequest;
import com.bazan.hospital.doctors.DTOs.CreateDoctorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    @GetMapping("{id}")
    public ResponseEntity<CreateDoctorResponse> getById(@PathVariable("id") long id) {
        try {
            var doctor = doctorService.getById(id);
            var response = CreateDoctorResponse.Success(doctor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateDoctorResponse> create(
            @RequestBody CreateDoctorRequest request
    ) {
        try {
            var doctor = doctorService.create(request);
            var response = CreateDoctorResponse.Success(doctor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = CreateDoctorResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
