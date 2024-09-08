import {Component, OnInit} from '@angular/core';
import {TransferDto} from "../../model/backend";
import {BackendService} from "../../service/backend.service";
import {TableModule} from "primeng/table";
import {NgClass} from "@angular/common";
import {MenuItem} from "primeng/api";
import {ContextMenuModule} from "primeng/contextmenu";

@Component({
  selector: 'app-transfer-overview-page',
  standalone: true,
  imports: [
    TableModule,
    NgClass,
    ContextMenuModule
  ],
  templateUrl: './transfer-overview-page.component.html',
  styleUrl: './transfer-overview-page.component.scss'
})
export class TransferOverviewPageComponent implements OnInit {

  transferDtos: TransferDto[] = [];
  items!: MenuItem[];
  selectedTransfer!: TransferDto;
  isApproveMenuItemVisible: boolean = false;

  constructor(private backendService: BackendService) {
  }

  ngOnInit(): void {
    this.backendService.getTransfers().subscribe((res) => {
      this.transferDtos = res;
      console.log(this.transferDtos.map(t => t.isChange));
    })

    this.items = [
      {
        label: 'Approve',
        icon: 'pi pi-verified',
        visible: this.selectedTransfer?.isChange,
        command: () => this.viewProduct(this.selectedTransfer)
      },
      {label: 'Bearbeiten', icon: 'pi pi-file-edit', command: () => this.deleteProduct(this.selectedTransfer)}
    ];
  }

  viewProduct(transferDto: TransferDto) {
  }

  deleteProduct(transferDto: TransferDto) {
  }
}
