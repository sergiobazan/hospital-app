import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HospitalRequest } from './models/HospitalRequest';
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

  createHospital(hospital: HospitalRequest) {
    return this.http.post<Response<HospitalResponse>>(`${this.url}`, hospital);
  }
}
