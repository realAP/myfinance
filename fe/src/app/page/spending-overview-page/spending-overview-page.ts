import { Component, OnInit, ChangeDetectionStrategy, inject, viewChild } from '@angular/core';
import {SpendingCategoryBlockDto, SpendingCreationDto, SpendingRowDto} from "../../model/backend";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgClass} from "@angular/common";
import {ContextMenu, ContextMenuModule} from "primeng/contextmenu";
import {MenuItem, MessageService} from "primeng/api";
import {DialogModule} from "primeng/dialog";
import {BackendService} from "../../service/backend/backend";

import {SpendingForm, SpendingFormDto} from "../../component/forms/spending-form/spending-form";

@Component({
  selector: 'app-spending-overview-page',
  standalone: true,
  imports: [
    TableModule,
    ContextMenuModule,
    DialogModule,
    SpendingForm,
    NgClass
  ],
  templateUrl: './spending-overview-page.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './spending-overview-page.scss'
})
export class SpendingOverviewPage implements OnInit {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  diffBetweenInAndOut: number = 0;
  spendingSum: number = 0;
  spendingCategoryBlockDtos: SpendingCategoryBlockDto[] = [];
  items!: MenuItem[];
  selectedSpendingRowDto!: SpendingRowDto;

  isEditDialogOpen: boolean = false;
  spendingFormDto?: SpendingFormDto;
  longPressTimeout: any;

  readonly cm = viewChild.required<ContextMenu>('cm');

  ngOnInit(): void {
    this.loadData();

    this.items = [
      {
        label: 'Bearbeiten', icon: 'pi pi-file-edit', command: () => {
          this.isEditDialogOpen = true;
        }
      },
      {
        label: 'Löschen', icon: 'pi pi-trash', command: () => {
          this.backendService.deleteSpending(this.selectedSpendingRowDto.id).subscribe({
              next: () => {
                this.loadData();
                this.messageService.add({severity: 'success', summary: 'Success', detail: 'Spending deleted'});
              }
            }
          )
        }
      }
    ];
  }

  onTouchStart(event: TouchEvent, spendingRow: SpendingRowDto) {
    this.updateContextMenu({data: spendingRow} as TableContextMenuSelectEvent)
    this.longPressTimeout = setTimeout(() => {
      this.cm().show(event);
    }, 500);
  }

  onTouchEnd() {
    clearTimeout(this.longPressTimeout);
  }


  private loadData() {
    this.backendService.getSpendingCategoryBlockDto().subscribe((res) => {
      this.spendingCategoryBlockDtos = res;
    })
    this.backendService.getSpendingSum().subscribe((res) => {
      this.spendingSum = res;
    })
    this.backendService.getDiffBetweenInAndOut().subscribe((res) => {
      this.diffBetweenInAndOut = res;
    })
  }

  updateContextMenu($event: TableContextMenuSelectEvent) {
    this.selectedSpendingRowDto = $event.data;
    const spendingCreationDto: SpendingCreationDto = {
      id: this.selectedSpendingRowDto.id,
      categoryId: this.selectedSpendingRowDto.categoryId,
      description: this.selectedSpendingRowDto.description,
      amount: this.selectedSpendingRowDto.amount,
      ruleId: this.selectedSpendingRowDto.ruleId,
      transferId: this.selectedSpendingRowDto.transferId
    }

    this.spendingFormDto = {
      spendingCreationDto: spendingCreationDto,
      isPreFilled: true
    };
  }

  onEditSpending(spendingCreationDto: SpendingCreationDto) {
    spendingCreationDto.id = this.selectedSpendingRowDto.id;
    this.backendService.editSpending(this.selectedSpendingRowDto.id, spendingCreationDto).subscribe({
        next: () => {
          this.loadData();
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Spending ' + spendingCreationDto.description + ' updated'});
        }
      }
    )
    this.isEditDialogOpen = false;
  }
}
