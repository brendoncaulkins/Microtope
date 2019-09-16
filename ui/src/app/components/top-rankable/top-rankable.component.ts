import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import { IRankable} from 'src/app/models/IRankable';
import {SelectedService} from 'src/app/services/selected.service';
import { Observable, Subscription } from 'rxjs';
import { topN } from 'src/app/utils/IRankable.functions';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent implements OnInit,OnDestroy{
  private TOP_COUNT = 3;

  selectionSub: Subscription;
  selectedItem:IRankable;
  
  rankingSub: Subscription;
  sortedItems: IRankable[] = [];

  @Input() items: Observable<IRankable[]>;

  constructor(public selection: SelectedService<IRankable>) { }

  ngOnInit() {
    this.selectionSub= this.selection.selected$.subscribe(
      newSelection => {this.selectedItem = newSelection});
    this.rankingSub = this.items.subscribe(
      newItems => {this.sortedItems = topN(newItems,this.TOP_COUNT)} 
    )
  }

  ngOnDestroy(){
    this.selectionSub && this.selectionSub.unsubscribe();
    this.rankingSub && this.rankingSub.unsubscribe();
  }

  onSelect(item:IRankable): void {
    this.selection.select(item);
  }

}
