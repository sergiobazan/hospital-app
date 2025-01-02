import { Component, OnInit } from '@angular/core';
import { PatientService } from './patient.service';
import { Patient } from './models/Patient';
import { PatientRequest } from './models/PatientRequest';
import { ModalComponent } from 'src/app/components/modal/modal.component';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: []
})
export class PatientsComponent implements OnInit {
  patients: Patient[] = [];
  patientRequest: PatientRequest = {
    name: '',
    birthDate: new Date(),
    sex: 'MALE',
    phone: '',
    hospitalId: 1
  }

  constructor(private service: PatientService) {}
  
  ngOnInit(): void {
    this.getPatients();
  }

  private getPatients() {
    this.service.getAllPatients().subscribe({
      next: (response) => this.patients = response,
      error: ({error}) => console.error(error)
    })
  }

  protected onSubmit(modal: ModalComponent) {
    this.service.createPatient(this.patientRequest).subscribe({
      next: (response) => {
        if (response.success) {
          this.patientRequest = {
            name: '',
            birthDate: new Date(),
            sex: 'MALE',
            phone: '',
            hospitalId: 1
          }
          this.getPatients();
          modal.close();
        }
      },
      error: ({error}) => console.error(error)
    })
  }
}
