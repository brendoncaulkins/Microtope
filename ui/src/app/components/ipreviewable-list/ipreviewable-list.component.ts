
import { Component, Input } from '@angular/core';
import { SelectedService } from 'src/app/services/selected.service';

import { IPreviewable } from 'src/app/models/IPreviewable';

import {selectionComponent} from "../selectionComponent";

@Component({
  selector: 'app-ipreviewable-list',
  templateUrl: './ipreviewable-list.component.html',
  styleUrls: ['./ipreviewable-list.component.css']
})
export class IPreviewableListComponent<T extends IPreviewable> extends selectionComponent<T>  {

  
  @Input() items: T[];

  constructor(private injectedSelectionService:SelectedService<T>) {super(injectedSelectionService);}


}