import { Component, OnInit } from '@angular/core';
import { DoctorService } from './doctor.service';
import { Doctor, Specialty } from './models/Doctor';
import { DoctorRequest } from './models/DoctorRequest';
import { ModalComponent } from 'src/app/components/modal/modal.component';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: []
})
export class DoctorsComponent implements OnInit {
  doctors: Doctor[] = [];
  specialties: Specialty[] = [];

  hospitalId: number = 1; // TODO: change to dynamic
  specialtySelcted: number = 1;
  doctorRequest: DoctorRequest = {
    name: '',
    phone: ''
  };

  constructor(private service: DoctorService) {}
 
  ngOnInit(): void {
    this.getDoctors();
    this.getSpecialties();
  }

  protected onSubmit(modal: ModalComponent) {
    this.doctorRequest = {
      ...this.doctorRequest,
      specialtyId: this.specialtySelcted,
      hospitalId: this.hospitalId
    }
    
    this.service.createDoctor(this.doctorRequest).subscribe({
      next: (response) => {
        if (response.success) {
          this.doctorRequest = {
            name: '',
            phone: '',
          }
          this.getDoctors();
          modal.close();
        }
      },
      error: ({error}) => console.error(error)
    })
  }

  private getSpecialties() {
    this.service.getSpecialties().subscribe({
      next: (response) => {
        this.specialties = response;
      },
      error: ({error}) => console.error(error)
    });
  }

  private getDoctors() {
    this.service.getAllDoctors().subscribe({
      next: (response) => {
        this.doctors = response;
      },
      error: ({error}) => console.error(error)
    });
  }
}
