import { Component } from '@angular/core';
import { ChartData, ChartOptions } from 'chart.js';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: []
})
export class HomeComponent {
  chartOptions: ChartOptions = {
    responsive: true
  };

  chartData: ChartData<'bar'> = {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [
      {
        data: [12, 19, 3, 5, 2, 3],
        label: 'Seria A'
      },
      { data: [28, 48, 40, 19, 86, 27, 90], 
        label: 'Series B' 
      },
    ]
  };

  chartType: 'bar' = 'bar';
}
