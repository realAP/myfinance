import {Component, OnInit} from '@angular/core';
import {BackendService} from "../../service/backend.service";
import {SpendingCategoryBlockDto} from "../../model/backend";
import {TableModule} from "primeng/table";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-spending-overview-page',
  standalone: true,
  imports: [
    TableModule,
    NgForOf
  ],
  templateUrl: './spending-overview-page.component.html',
  styleUrl: './spending-overview-page.component.scss'
})
export class SpendingOverviewPageComponent implements OnInit {

  spendingCategoryBlockDtos: SpendingCategoryBlockDto[] = [];

  constructor(private backendService: BackendService) {
  }

  ngOnInit(): void {
    this.backendService.getSpendingCategoryBlockDto().subscribe((res) => {
      this.spendingCategoryBlockDtos = res;
    })
  }

}
