import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {filter, flatMap, map, take} from 'rxjs/operators';
import {IPreviewable} from 'src/app/models/IPreviewable';
import {IRankable} from 'src/app/models/IRankable';
import {SelectedService} from 'src/app/services/selected.service';
import {topNCurry} from 'src/app/utils/IRankable.functions';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent<T extends IPreviewable & IRankable> implements OnInit {
  private TOP_COUNT = 3;

  sortedItems: Observable<T[]>;

  @Input() items: Observable<T[]>;

  constructor(public selection: SelectedService<T>) { }

  ngOnInit() {
    this.sortedItems = this.items.pipe(
      map(topNCurry(this.TOP_COUNT))
    ) as Observable<T[]>;
  }


  onSelect(item: T): void {
    this.sortedItems.pipe(
      take(1),      // this will cause automatic unsubscribe after first emit
      flatMap(items => items),  // flatten array
      filter(i => item === i)
    ).subscribe(x => this.selection.select(x));
  }

}
