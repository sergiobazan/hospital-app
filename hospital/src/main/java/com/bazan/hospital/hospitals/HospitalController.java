package com.bazan.hospital.hospitals;

import com.bazan.hospital.hospitals.DTOs.CreateHospitalRequest;
import com.bazan.hospital.hospitals.DTOs.CreateHospitalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    private final IHospitalService hospitalService;

    @GetMapping("{id}")
    public ResponseEntity<CreateHospitalResponse> getById(
            @PathVariable("id") long id
    ) {
        try {
            var hospital = hospitalService.getById(id);
            var response = CreateHospitalResponse.Success(hospital);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateHospitalResponse> create(
            @RequestBody CreateHospitalRequest request
    ) {
        try {
            var result = hospitalService.create(request);
            var response = CreateHospitalResponse.Success(result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            var response = CreateHospitalResponse.Failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
