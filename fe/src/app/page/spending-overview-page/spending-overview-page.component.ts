import {Component, OnInit} from '@angular/core';
import {BackendService} from "../../service/backend.service";
import {SpendingCategoryBlockDto, SpendingCreationDto, SpendingRowDto} from "../../model/backend";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgForOf} from "@angular/common";
import {ContextMenuModule} from "primeng/contextmenu";
import {MenuItem, MessageService} from "primeng/api";
import {DialogModule} from "primeng/dialog";
import {RuleFormComponent} from "../../component/rule-form/rule-form.component";
import {SpendingFormComponent, SpendingFormDto} from "../../component/spending-form/spending-form.component";
import {subscribeOn} from "rxjs";

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

  spendingSum: number = 0;
  spendingCategoryBlockDtos: SpendingCategoryBlockDto[] = [];
  items!: MenuItem[];
  selectedSpendingRowDto!: SpendingRowDto;

  isEditDialogOpen: boolean = false;
  spendingFormDto?: SpendingFormDto;

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

  private loadData() {
    this.backendService.getSpendingCategoryBlockDto().subscribe(
      {
        next: (res) => {
          this.spendingCategoryBlockDtos = res;
        },
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Failed to load spending data'});
        }
      }
    )
    this.backendService.getSpendingSum().subscribe(
      {
        next: (res) => {
          this.spendingSum = res;
        },
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Failed to load spending sum'});
        }
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
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Transfer updated'});
        },
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Failed to update spending'});
        }
      }
    )
    this.isEditDialogOpen = false;
  }

  protected readonly subscribeOn = subscribeOn;
}
