export interface Patient {
  id: number
  name: string
  phone: string
  sex: 'MALE' | 'FEMALE'
  birthDate: Date
}