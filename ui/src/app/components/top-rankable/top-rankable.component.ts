import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import {Rankable} from 'src/app/models/IRankable';
import {SelectedService} from 'src/app/services/selected.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent<T extends Rankable, IPreviewable> {
  private TOP_COUNT = 3;
  sortedItems: Array<T> = [];

  @Input()
  public set items(items: Array<T>) {
    this.sortedItems = items.slice(0, this.TOP_COUNT);
  }

  constructor(public selection: SelectedService<T>) { }
}
