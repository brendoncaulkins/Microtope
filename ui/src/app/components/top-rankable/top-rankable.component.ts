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

  top3Items:T[];

  constructor(private selection:SelectedService<T>) { }

  selectionSub: Subscription;
  selectedItem:T;

  ngOnInit() {
    this.selectionSub= this.selection.subject$.subscribe(
      newSelection => {this.selectedItem = newSelection});
    //Sort the rankable items according to their logic and select top 3
    this.top3Items=
      this.items.sort((i1,i2)=> i1.compare(i2)).slice(0,3);
  }

  ngOnDestroy(){
    this.selectionSub && this.selectionSub.unsubscribe();
  }

  onSelect(item:T): void {
    this.selection.set(item);
  }

}
