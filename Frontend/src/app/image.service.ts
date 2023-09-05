import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) { }

  uploadCsvFile(file: File) {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post('/api/csv/upload', formData);
  }
}
