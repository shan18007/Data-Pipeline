import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  private apiUrl = 'http://localhost:8080/api/v1/pipeline';

  constructor(private http: HttpClient) {}

  uploadFile(formData: FormData) {
    return this.http.post(`${this.apiUrl}/upload`, formData);
  }

  getProductSales() {
    return this.http.get(`${this.apiUrl}/product`);
  }

  getCategoryWiseSales() {
    return this.http.get(`${this.apiUrl}/category`);
  }
}
