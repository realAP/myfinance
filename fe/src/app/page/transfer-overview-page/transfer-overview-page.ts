import { Component, OnInit, ChangeDetectionStrategy, inject, viewChild } from '@angular/core';
import {TransferCreationDto, TransferDto} from "../../model/backend";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgClass} from "@angular/common";
import {MenuItem, MessageService} from "primeng/api";
import {ContextMenu, ContextMenuModule} from "primeng/contextmenu";
import {DialogModule} from "primeng/dialog";

import {BackendService} from "../../service/backend/backend";
import {TransferForm, TransferFormDto} from "../../component/forms/transfer-form/transfer-form";

@Component({
  selector: 'app-transfer-overview-page',
  standalone: true,
  imports: [
    TableModule,
    NgClass,
    ContextMenuModule,
    DialogModule,
    TransferForm,
    ],
  templateUrl: './transfer-overview-page.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './transfer-overview-page.scss'
})
export class TransferOverviewPage implements OnInit {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  transferDtos: TransferDto[] = [];
  items!: MenuItem[];
  selectedTransfer!: TransferDto;
  isEditDialogOpen: boolean = false;
  transferFormDto?: TransferFormDto;
  longPressTimeout: any;

  readonly cm = viewChild.required<ContextMenu>('cm');

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

  onTouchStart(event: TouchEvent, transferRow: TransferDto) {
    this.updateContextMenu({data: transferRow} as TableContextMenuSelectEvent)
    this.longPressTimeout = setTimeout(() => {
      this.cm().show(event);
    }, 500);
  }

  onTouchEnd() {
    clearTimeout(this.longPressTimeout);
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
