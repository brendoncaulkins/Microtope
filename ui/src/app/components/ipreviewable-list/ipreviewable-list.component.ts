
import { Component, Input } from '@angular/core';
import { SelectedService } from 'src/app/services/selected.service';

import { IPreviewable } from 'src/app/models/IPreviewable';

import { Subscription, Observable } from 'rxjs';

@Component({
  selector: 'app-ipreviewable-list',
  templateUrl: './ipreviewable-list.component.html',
  styleUrls: ['./ipreviewable-list.component.css']
})
export class IPreviewableListComponent{

  @Input() items: Observable<IPreviewable[]>;

  private selectionSub: Subscription;
  selectedItem:IPreviewable;
  
    
  constructor(private selection:SelectedService<IPreviewable>) {}

  ngOnInit() {
    this.selectionSub= this.selection.selected$.subscribe(
      newSelection => {this.selectedItem = newSelection});
  }

  ngOnDestroy(){
    this.selectionSub && this.selectionSub.unsubscribe();
  }

  onSelect(item:IPreviewable): void {
    this.selection.select(item);
  }
  
}
