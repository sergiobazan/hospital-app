import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'src/app/shared/Response';
import { environment } from 'src/environments/environment';
import { Patient } from './models/Patient';
import { PatientRequest } from './models/PatientRequest';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private url = `${environment.apiUrl}/patients`

  constructor(private http: HttpClient) { }

  getPatientById(id: number) {
    return this.http.get<Response<Patient>>(`${this.url}/${id}`);
  }

  createPatient(patient: PatientRequest) {
    return this.http.post<Response<Patient>>(this.url, patient);
  }

  getAllPatients() {
    return this.http.get<Patient[]>(this.url);
  }
}
