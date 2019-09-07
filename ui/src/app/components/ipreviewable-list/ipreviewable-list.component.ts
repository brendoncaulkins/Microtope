
import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { SelectedService } from 'src/app/services/selected.service';

import { Subscription } from 'rxjs';
import { IPreviewable } from 'src/app/models/IPreviewable';

@Component({
  selector: 'app-ipreviewable-list',
  templateUrl: './ipreviewable-list.component.html',
  styleUrls: ['./ipreviewable-list.component.css']
})
export class IPreviewableListComponent<T extends IPreviewable> implements OnInit, OnDestroy {

  
  @Input() items: T[];

  constructor(private selection:SelectedService<T>) { }

  selectionSub: Subscription;
  selectedItem:T;

  ngOnInit() {
    this.selectionSub= this.selection.subject$.subscribe(
      newSelection => {this.selectedItem = newSelection});
  }

  ngOnDestroy(){
    this.selectionSub && this.selectionSub.unsubscribe();
  }

  onSelect(item:T): void {
    this.selection.set(item);
  }

}