import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Response } from 'src/app/shared/Response';
import { HospitalResponse } from './models/HospitalResponse';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {
  private url = `${environment.apiUrl}/hospitals`

  constructor(private http: HttpClient) { }

  getHospitalById(id: number) {
    return this.http.get<Response<HospitalResponse>>(`${this.url}/${id}`);
  }

  createHospital(hospital: FormData) {
    return this.http.post<Response<HospitalResponse>>(`${this.url}`, hospital);
  }

  updateHospital(id: number, hospital: FormData) {
    return this.http.put<string>(`${this.url}/${id}`, hospital);
  }
}
