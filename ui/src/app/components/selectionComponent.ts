import { Subscription } from 'rxjs';
import { SelectedService } from '../services/selected.service';
import { OnInit,OnDestroy, Input } from '@angular/core';

export abstract class selectionComponent<T> implements OnInit,OnDestroy {

    protected selectionSub: Subscription;
    protected selectedItem:T;
    
    protected selection: SelectedService<T>;
    
  constructor(selection:SelectedService<T>) { 
      this.selection=selection;
  }

  ngOnInit() {
    this.selectionSub= this.selection.selected$.subscribe(
      newSelection => {this.selectedItem = newSelection});
  }

  ngOnDestroy(){
    this.selectionSub && this.selectionSub.unsubscribe();
  }

  onSelect(item:T): void {
    this.selection.select(item);
  }
}