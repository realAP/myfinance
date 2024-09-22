import {Component, OnInit} from '@angular/core';
import {TransferCreationDto, TransferDto} from "../../model/backend";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgClass, NgIf} from "@angular/common";
import {MenuItem, MessageService} from "primeng/api";
import {ContextMenuModule} from "primeng/contextmenu";
import {DialogModule} from "primeng/dialog";
import {Button} from "primeng/button";
import {TransferFormComponent, TransferFormDto} from "../../component/transfer-form/transfer-form.component";
import {BackendService} from "../../service/backend/backend.service";

@Component({
  selector: 'app-transfer-overview-page',
  standalone: true,
  imports: [
    TableModule,
    NgClass,
    ContextMenuModule,
    DialogModule,
    Button,
    TransferFormComponent,
    NgIf
  ],
  templateUrl: './transfer-overview-page.component.html',
  styleUrl: './transfer-overview-page.component.scss'
})
export class TransferOverviewPageComponent implements OnInit {

  transferDtos: TransferDto[] = [];
  items!: MenuItem[];
  selectedTransfer!: TransferDto;
  isEditDialogOpen: boolean = false;
  transferFormDto?: TransferFormDto;

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
      {
        label: 'Bearbeiten', icon: 'pi pi-file-edit', command: () => {
          console.log("openEditDialog")
          this.isEditDialogOpen = true;
        }
      },
      {
        label: 'Löschen', icon: 'pi pi-trash', command: () => {
          this.backendService.deleteTransfer(this.selectedTransfer.id).subscribe({
              next: () => {
                this.loadTransferDtos();
                this.messageService.add({severity: 'success', summary: 'Success', detail: 'Transfer deleted'});
              }
            }
          )
        }
      }
    ];
  }

  private loadTransferDtos() {
    this.backendService.getTransfers().subscribe((res) => {
      this.transferDtos = res;
    })
  }

  approveChange(transferDto: TransferDto) {
    this.backendService.confirmTransferChange(transferDto.id).subscribe({
        next: () => {
          this.loadTransferDtos();
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Approved Changes'});
        }
      }
    )
  }

  updateContextMenu(event: TableContextMenuSelectEvent) {
    this.selectedTransfer = event.data;
    const transferCreationDto: TransferCreationDto =
      {
        description: this.selectedTransfer.description,
        dateOfExecution: this.selectedTransfer.dateOfExecution,
        fromBankId: this.selectedTransfer.fromBankNameId,
        toBankId: this.selectedTransfer.toBankNameId
      }
    this.transferFormDto = {
      transferCreationDto: transferCreationDto,
      isPreFilled: true
    };
    this.items[0].visible = this.selectedTransfer?.isChange;
  }

  onEditTransfer(transferCreationDto: TransferCreationDto) {
    this.backendService.editTransfer(this.selectedTransfer.id, transferCreationDto).subscribe({
        next: () => {
          this.loadTransferDtos();
          this.messageService.add({
            severity: 'success',
            summary: 'Edited Transfer:',
            detail: transferCreationDto.description
          });
          this.isEditDialogOpen = false;
        }
      }
    );
  }
}
