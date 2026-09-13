import { Component, OnInit, ChangeDetectionStrategy, inject, viewChild } from '@angular/core';
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {BackendService} from "../../service/backend/backend";
import {MenuItem, MessageService} from "primeng/api";
import {IncomeCreationDto, IncomeDto} from "../../model/backend";
import {ContextMenu, ContextMenuModule} from "primeng/contextmenu";
import {IncomeFormComponent, IncomeFormDto} from "../../component/forms/income-form/income-form";
import {DialogModule} from "primeng/dialog";

@Component({
  selector: 'app-income-overview-page',
  standalone: true,
  imports: [
    TableModule,
    DialogModule,
    IncomeFormComponent,
    ContextMenuModule
  ],
  templateUrl: './income-overview-page.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './income-overview-page.scss'
})
export class IncomeOverviewPageComponent implements OnInit {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  incomeDtos: IncomeDto[] = [];
  items!: MenuItem[];
  selectedIncome!: IncomeDto;
  isEditDialogOpen: boolean = false;
  incomeFormDto?: IncomeFormDto;

  readonly cm = viewChild.required<ContextMenu>('cm');
  longPressTimeout: any;
  incomeSum?: number;

  ngOnInit(): void {
    this.loadData();

    this.items = [
      {
        label: 'Bearbeiten', icon: 'pi pi-file-edit', command: () => {
          this.isEditDialogOpen = true;
        },
      },
      {
        label: 'Löschen', icon: 'pi pi-trash', command: () => {
          this.backendService.deleteIncome(this.selectedIncome.id).subscribe({
            next: () => {
              this.loadData();
              this.messageService.add({
                severity: 'success', summary: 'Success',
                detail: 'Income: ' + this.selectedIncome.description + ' deleted'
              });
            }
          })
        },
      }
    ]
    ;
  }

  onTouchStart(event: TouchEvent, incomeDto: IncomeDto) {
    this.updateContextMenu({data: incomeDto} as TableContextMenuSelectEvent)
    this.longPressTimeout = setTimeout(() => {
      this.cm().show(event);
    }, 500);
  }

  onTouchEnd() {
    clearTimeout(this.longPressTimeout);
  }

  updateContextMenu(event: TableContextMenuSelectEvent) {
    this.selectedIncome = event.data;
    const incomeCreationDto: IncomeCreationDto =
      {
        description: this.selectedIncome.description,
        amount: this.selectedIncome.amount
      }
    this.incomeFormDto = {
      incomeCreationDto: incomeCreationDto,
      isPreFilled: true
    };
  }

  onEditIncome(incomeCreationDto: IncomeCreationDto) {
    this.backendService.editIncome(this.selectedIncome.id, incomeCreationDto).subscribe({
        next: () => {
          this.loadData();
          this.messageService.add({
            severity: 'success',
            summary: 'Edited Rule:',
            detail: incomeCreationDto.description
          });
          this.isEditDialogOpen = false;
        }
      }
    );
  }

  private loadData() {
    this.backendService.getIncomes().subscribe((incomeDtos: IncomeDto[]) => {
      this.incomeDtos = incomeDtos;
    });
    this.backendService.getIncomeSum().subscribe((incomeSum: number) => {
      this.incomeSum = incomeSum;
    });
  }

}
