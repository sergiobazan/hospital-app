import { Component, OnInit } from '@angular/core';
import { HospitalService } from './hospital.service';
import { HospitalResponse } from './models/HospitalResponse';
import { ModalComponent } from 'src/app/components/modal/modal.component';
import { HospitalRequest } from './models/HospitalRequest';

@Component({
  selector: 'app-hospital',
  templateUrl: './hospital.component.html',
  styleUrls: []
})
export class HospitalComponent implements OnInit {
  hospital: HospitalResponse | null = null;
  selectedFile: File | null = null;
  hospitalRequest: HospitalRequest = {
    name: '',
    address: '',
    phone: '',
    email: ''
  };

  constructor(private service: HospitalService) {}
  
  ngOnInit(): void {
    this.getHospital();
  }

  private getHospital() {
    this.service.getHospitalById(1).subscribe({
      next: (response) => {
        if (response.success) {
          this.hospital = response.data;
          if (this.hospital) {
            this.hospitalRequest = {
              name: this.hospital.name,
              address: this.hospital.address,
              phone: this.hospital.phone,
              email: this.hospital.email
            }
          }
          return;
        }
        console.error(response.message);
      },
      error: ({ message }) => console.error(message)
    })
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input?.files?.[0]) {
      this.selectedFile = input.files[0];
    }
  }

  onEditHospital(modal: ModalComponent) {
    const formData = new FormData();
    formData.append('name', this.hospitalRequest.name);
    formData.append('address', this.hospitalRequest.address);
    formData.append('phone', this.hospitalRequest.phone);
    formData.append('email', this.hospitalRequest.email);

    if (this.selectedFile) {
      formData.append('logo', this.selectedFile);
    }

    this.service.updateHospital(this.hospital?.id!, formData).subscribe({
      next: (response) => {
        if (response) {
          this.getHospital();
          return;
        }
      },
      error: ({error}) => console.error(error)
    })
    modal.close();
  }
}
