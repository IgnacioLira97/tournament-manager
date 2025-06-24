export interface Tournament {
  id?: number;
  name: string;
  date: string; // ISO string, e.g., "2025-06-22"
  status: 'PLANNED' | 'ONGOING' | 'COMPLETED'; // match your enum values exactly
}
