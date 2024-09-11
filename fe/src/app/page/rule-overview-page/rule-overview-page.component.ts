import {Component, OnInit} from '@angular/core';
import {RuleCreationDto, RuleDto} from "../../model/backend";
import {MenuItem, MessageService} from "primeng/api";
import {BackendService} from "../../service/backend.service";
import {TableContextMenuSelectEvent, TableModule} from "primeng/table";
import {NgClass} from "@angular/common";
import {DialogModule} from "primeng/dialog";
import {ContextMenuModule} from "primeng/contextmenu";
import {RuleFormComponent} from "../../component/rule-form/rule-form.component";

@Component({
  selector: 'app-rule-overview-page',
  standalone: true,
  imports: [
    TableModule,
    NgClass,
    DialogModule,
    ContextMenuModule,
    RuleFormComponent
  ],
  templateUrl: './rule-overview-page.component.html',
  styleUrl: './rule-overview-page.component.scss'
})
export class RuleOverviewPageComponent implements OnInit {

  ruleDtos: RuleDto[] = [];
  items!: MenuItem[];
  selectedRule!: RuleDto;
  isEditDialogOpen: boolean = false;
  ruleFormDto?: any;

  constructor(private backendService: BackendService,
              private messageService: MessageService
  ) {
  }

  ngOnInit(): void {
    this.loadRuleDtos();

    this.items = [
      {
        label: 'Approve',
        icon: 'pi pi-verified',
        visible: false, // depends on selected row
        command: () => this.approveChange(this.selectedRule)
      },
      {
        label: 'Bearbeiten', icon: 'pi pi-file-edit', command: () => {
          this.isEditDialogOpen = true;
        }
      }
    ];
  }

  private loadRuleDtos() {
    this.backendService.getRules().subscribe((res) => {
      this.ruleDtos = res;
    })
  }

  approveChange(ruleDto: RuleDto) {
    this.backendService.confirmRuleChange(ruleDto.id).subscribe({
        next: () => {
          this.loadRuleDtos();
          this.messageService.add({severity: 'success', summary: 'Success', detail: 'Approved Changes'});
        }
      }
    )
  }

  updateContextMenu(event: TableContextMenuSelectEvent) {
    this.selectedRule = event.data;
    const ruleCreationDto: RuleCreationDto =
      {
        description: this.selectedRule.description,
        dateOfExecution: this.selectedRule.dateOfExecution,
        fromSpaceId: this.selectedRule.fromSpaceId,
        toSpaceId: this.selectedRule.toSpaceId
      }
    this.ruleFormDto = {
      ruleCreationDto: ruleCreationDto,
      isPreFilled: true
    };
    this.items[0].visible = this.selectedRule?.isChange;
  }

  onEditRule(ruleCreationDto: RuleCreationDto) {
    this.backendService.editRule(this.selectedRule.id, ruleCreationDto).subscribe({
        next: () => {
          this.loadRuleDtos();
          this.messageService.add({
            severity: 'success',
            summary: 'Edited Rule:',
            detail: ruleCreationDto.description
          });
          this.isEditDialogOpen = false;
        }
      }
    );
  }
}
