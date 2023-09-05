import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'node_modules/chart.js';
import { FileUploadService } from '../fileupload.service';

Chart.register(...registerables);
@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css'],
})
export class DisplayComponent implements OnInit {
  public barChart: any;
  public pieChart: any;


  constructor(private _fileUpoadService: FileUploadService) {
    console.log('constructor called..');
  }

  ngOnInit(): void {
    console.log('onInit called..');

    this._fileUpoadService.getCategoryWiseSales().subscribe(
      (response: any) => {
        console.error('success:', response);
        console.error('success:', response.data);
        this.createPieChart(response.data);
      },
      (error) => {
        console.error('Error getting data :', error);
      }
    );

    this._fileUpoadService.getProductSales().subscribe(
      (response: any) => {
        console.error('success:', response);
        console.error('success:', response.data);
        this.createBarChart(response.data);
      },
      (error) => {
        console.error('Error getting data :', error);
      }
    );
  }

  createBarChart(res: any) {
    this.barChart = new Chart('barChart', {
      type: 'bar', 

      data: {
        // values on X-Axis
        labels: Object.keys(res),
        datasets: [
          {
            label: 'Total Sales',
            data: Object.values(res),
            backgroundColor: [
              'rgba(63, 81, 181, 0.5)',
              'rgba(77, 182, 172, 0.5)',
              'rgba(66, 133, 244, 0.5)',
              'rgba(156, 39, 176, 0.5)',
              'rgba(233, 30, 99, 0.5)',
              'rgba(66, 73, 244, 0.4)',
            ],
          },
        ],
      },
      options: {
        aspectRatio: 2.0,
      },
    });
  }


  createPieChart(res: any) {
    this.barChart = new Chart('pieChart', {
      type: 'pie', 

      data: {
        // values on X-Axis
        labels: Object.keys(res),
        datasets: [
          {
            label: 'Total sales',
            data: Object.values(res),
            backgroundColor: [
              'rgba(63, 81, 181, 0.5)',
              'rgba(77, 182, 172, 0.5)',
              'rgba(66, 133, 244, 0.5)',
              'rgba(156, 39, 176, 0.5)',
              'rgba(233, 30, 99, 0.5)',
              'rgba(66, 73, 244, 0.4)',
            ],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        aspectRatio: 2.0,
      },
    });
  }

}
