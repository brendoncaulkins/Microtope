import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import { IRankable} from 'src/app/models/IRankable';
import {SelectedService} from 'src/app/services/selected.service';
import { Observable, Subscription } from 'rxjs';
import { topN } from 'src/app/utils/IRankable.functions';
import { IPreviewable } from 'src/app/models/IPreviewable';
import { tap,filter, map } from 'rxjs/operators';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent<T extends IPreviewable & IRankable> implements OnInit,OnDestroy{
  private TOP_COUNT = 3;

  selectionSub: Subscription;
  selectedItem:T;
  
  rankingSub: Subscription;
  sortedItems: T[] = [];

  @Input() items: Observable<T[]>;

  constructor(public selection: SelectedService<T>) { }

  ngOnInit() {
    this.selectionSub= this.selection.selected$.subscribe(
      newSelection => {this.selectedItem = newSelection});

    this.rankingSub = this.items.subscribe(
      newItems => {this.sortedItems =  (topN(newItems,this.TOP_COUNT) as T[])  } 
    )
  }

  ngOnDestroy(){
    this.selectionSub && this.selectionSub.unsubscribe();
    this.rankingSub && this.rankingSub.unsubscribe();
  }

  onSelect(item:T): void {
    this.items.pipe(
      map(xs=>xs.filter(x=> x===item)),
      tap(xs=> this.selection.select(xs[0]))
    )
  }

}
