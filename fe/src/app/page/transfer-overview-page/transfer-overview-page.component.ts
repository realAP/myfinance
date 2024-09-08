import {Component, OnInit} from '@angular/core';
import {TransferDto} from "../../model/backend";
import {BackendService} from "../../service/backend.service";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgClass} from "@angular/common";
import {MenuItem, MessageService} from "primeng/api";
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

  constructor(private backendService: BackendService,
              private messageService: MessageService
  ) {
  }

  ngOnInit(): void {
    this.loadTransferDtos();

    this.items = [
      {
        label: 'Approve',
        icon: 'pi pi-verified',
        visible: false, // depends on selected row
        command: () => this.approveChange(this.selectedTransfer)
      },
      {label: 'Bearbeiten', icon: 'pi pi-file-edit', command: () => this.editTransfer(this.selectedTransfer)}
    ];
  }

  private loadTransferDtos() {
    this.backendService.getTransfers().subscribe((res) => {
      this.transferDtos = res;
    })
  }

  approveChange(transferDto: TransferDto) {
    this.backendService.confirmChange(transferDto.id).subscribe({
        next: () => {
          this.loadTransferDtos();
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Approved Changes'});
        }
      }
    )
  }

  editTransfer(transferDto: TransferDto) {
  }

  updateContextMenu(event: TableContextMenuSelectEvent) {
    this.selectedTransfer = event.data;
    this.items[0].visible = this.selectedTransfer?.isChange;
  }
}
