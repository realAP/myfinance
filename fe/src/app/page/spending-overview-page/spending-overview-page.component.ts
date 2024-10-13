import {Component, OnInit, ViewChild} from '@angular/core';
import {SpendingCategoryBlockDto, SpendingCreationDto, SpendingRowDto} from "../../model/backend";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgForOf} from "@angular/common";
import {ContextMenu, ContextMenuModule} from "primeng/contextmenu";
import {MenuItem, MessageService} from "primeng/api";
import {DialogModule} from "primeng/dialog";
import {RuleFormComponent} from "../../component/rule-form/rule-form.component";
import {SpendingFormComponent, SpendingFormDto} from "../../component/spending-form/spending-form.component";
import {subscribeOn} from "rxjs";
import {BackendService} from "../../service/backend/backend.service";

@Component({
  selector: 'app-spending-overview-page',
  standalone: true,
  imports: [
    TableModule,
    NgForOf,
    ContextMenuModule,
    DialogModule,
    RuleFormComponent,
    SpendingFormComponent
  ],
  templateUrl: './spending-overview-page.component.html',
  styleUrl: './spending-overview-page.component.scss'
})
export class SpendingOverviewPageComponent implements OnInit {

  diffBetweenInAndOut: number = 0;
  spendingSum: number = 0;
  spendingCategoryBlockDtos: SpendingCategoryBlockDto[] = [];
  items!: MenuItem[];
  selectedSpendingRowDto!: SpendingRowDto;

  isEditDialogOpen: boolean = false;
  spendingFormDto?: SpendingFormDto;
  longPressTimeout: any;

  @ViewChild('cm') cm!: ContextMenu;

  constructor(private backendService: BackendService,
              private messageService: MessageService
  ) {
  }

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
      this.cm.show(event);
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
