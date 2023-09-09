import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FileUploadService } from '../fileupload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {

  constructor(private http: HttpClient, public router:Router, private _fileUploadService: FileUploadService) {}

  selectedFile: File | null = null;

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadFile(): void {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      this._fileUploadService.uploadFile(formData).subscribe(
        {
          next: (res) => {
            console.log("res: ",res);
            this.router.navigate(['display']);
          },
          error: (err: any) => {
            console.log("error: ",err);
            alert("Invalid file");
          }
        }
        );
      
    } else {
      alert("please upload the file.")
    }
  }

  }