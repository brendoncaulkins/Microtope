import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {filter, flatMap, map, take, first} from 'rxjs/operators';
import {IPreviewable} from 'src/app/models/IPreviewable';
import {IRankable} from 'src/app/models/IRankable';
import {SelectedService} from 'src/app/services/selected.service';
import {topN} from 'src/app/utils/IRankable.functions';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent<T extends IPreviewable & IRankable> implements OnInit,OnDestroy {

  private TOP_COUNT = 3;

  sortedItems : Observable<T[]>;

  selectionSub : Subscription;
  selectedItem: T;

  @Input() items: Observable<T[]>;

  constructor(public selection: SelectedService<T>) { }

  ngOnInit() {
    this.sortedItems = (topN(this.items,this.TOP_COUNT) as Observable<T[]>);

    this.selectionSub = this.selection.selected$.subscribe(
      x => this.sortedItems.pipe(
        // Flatten Observable T[] to T[]
        flatMap( items => items),
        // Check if any fits the newly selected item
        first(i => this.helperEquals(i,x))
      ).toPromise()
      // If it exists, update local field
        .then(foundSelection => this.selectedItem = foundSelection)
      // Else set selected Item to null
        .catch(x => this.selectedItem = null)
    );
  }

  ngOnDestroy(): void {
    this.selectionSub.unsubscribe();
  }

  onSelect(item: T): void {
   this.selection.select(item);
  }

  private helperEquals(first: T, second: T): boolean {
    return first.id === second.id && first.name === second.name  && first.coins === second.coins && first.steps === second.steps;
  }

}
