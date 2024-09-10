import {Component} from '@angular/core';
import {CardModule} from "primeng/card";
import {BankCreationComponent} from "../../component/bank-creation/bank-creation.component";
import {CategoryCreationComponent} from "../../component/category-creation/category-creation.component";
import {RuleFormComponent} from "../../component/rule-form/rule-form.component";
import {TransferFormComponent} from "../../component/transfer-form/transfer-form.component";
import {SpaceFormComponent} from "../../component/space-form/space-form.component";
import {SpendingCreationComponent} from "../../component/spending-creation/spending-creation.component";
import {ConnectedSquaresComponent} from "../../component/connected-squares/connected-squares.component";
import {BackendService} from "../../service/backend.service";
import {MessageService} from "primeng/api";
import {RuleCreationDto, TransferCreationDto} from "../../model/backend";

@Component({
  selector: 'app-backoffice-page',
  standalone: true,
  imports: [
    BankCreationComponent,
    CategoryCreationComponent,
    RuleFormComponent,
    TransferFormComponent,
    SpaceFormComponent,
    SpendingCreationComponent,
    CardModule,
    ConnectedSquaresComponent
  ],
  templateUrl: './backoffice-page.component.html',
  styleUrl: './backoffice-page.component.scss'
})
export class BackofficePageComponent {


  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  onTransferCreate(transferCreationDto: TransferCreationDto) {
    this.backendService.createTransfer(transferCreationDto).subscribe();
    this.messageService.add({severity: 'success', summary: 'created Transfer:', detail: transferCreationDto.description});
  }

  onRuleCreate(ruleCreationDto: RuleCreationDto) {
    this.backendService.createRule(ruleCreationDto).subscribe();
    this.messageService.add({severity: 'success', summary: 'Success', detail: 'Created rule'});
  }
}
