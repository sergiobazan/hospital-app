
export interface Doctor {
  id: number
  name: string
  phone: string
  specialty: Specialty
}

export interface Specialty {
  id: number
  name: string
}