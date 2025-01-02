export interface AppointmentRequest {
  title: string
  start: Date
  finish: Date
  doctorId?: number
  patientId?: number
}