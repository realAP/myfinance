import {Component, OnInit} from '@angular/core';
import {BackendService} from "../../service/backend.service";
import {SpendingCategoryBlockDto} from "../../model/backend";

@Component({
  selector: 'app-spending-overview-page',
  standalone: true,
  imports: [],
  templateUrl: './spending-overview-page.component.html',
  styleUrl: './spending-overview-page.component.scss'
})
export class SpendingOverviewPageComponent {

  spendingCategoryBlockDtos: SpendingCategoryBlockDto[] = [];

  constructor(private backendService: BackendService) {
    backendService.getSpendingOverviewDto().subscribe((res ) => {
      this.spendingCategoryBlockDtos = res;
      console.log(res);
    })
  }

}
