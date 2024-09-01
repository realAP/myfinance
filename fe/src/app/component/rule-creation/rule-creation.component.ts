import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {InputGroupModule} from "primeng/inputgroup";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";
import {RuleCreationDto, SpaceDto} from "../../model/backend";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-rule-creation',
  standalone: true,
  imports: [
    Button,
    DropdownModule,
    FloatLabelModule,
    InputGroupAddonModule,
    InputGroupModule,
    InputNumberModule,
    InputTextModule,
    CalendarModule,
    FormsModule
  ],
  templateUrl: './rule-creation.component.html',
  styleUrl: './rule-creation.component.scss'
})
export class RuleCreationComponent implements OnInit {
  date: any;
  spaces: SpaceDto[] = [];
  selectedFromSpace: SpaceDto = {} as SpaceDto;
  selectedTargetSpace: SpaceDto = {} as SpaceDto;
  name: string = "";

  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.backendService.getSpaces().subscribe(spaceDtos => {
      this.spaces = spaceDtos;
    })
  }

  onClick() {
    if (!this.selectedFromSpace.id || !this.selectedTargetSpace.id || !this.date || !this.name) {
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Please fill in all fields'});
      return;
    }
    const ruleCreationDto: RuleCreationDto = {
      description: this.name,
      dateOfExecution: this.date.toISOString(),
      fromSpaceId: this.selectedFromSpace.id,
      toSpaceId: this.selectedTargetSpace.id,
      fromSpaceName: this.selectedFromSpace.name,
      toSpaceName: this.selectedTargetSpace.name,
    }

    this.backendService.createRule(ruleCreationDto).subscribe();
    this.messageService.add({severity: 'success', summary: 'Success', detail: 'Created rule'});
  }
}
