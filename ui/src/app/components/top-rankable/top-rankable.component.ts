import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Rankable } from 'src/app/models/IRankable';
import { SelectedService } from 'src/app/services/selected.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent<T extends Rankable,IPreviewable> implements OnInit,OnDestroy {
  
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

  topN(items:T[],n:number):T[]{
    return items.slice(0,n);
    //return items.sort((i1,i2)=> i1.compare(i2)).slice(0,n);
  }
}
