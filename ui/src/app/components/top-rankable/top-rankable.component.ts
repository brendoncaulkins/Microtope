import { Component,Input } from '@angular/core';
import { Rankable } from 'src/app/models/IRankable';
import { SelectedService } from 'src/app/services/selected.service';
import { selectionComponent } from '../selectionComponent';

@Component({
  selector: 'app-top-rankable',
  templateUrl: './top-rankable.component.html',
  styleUrls: ['./top-rankable.component.css']
})
export class TopRankableComponent<T extends Rankable,IPreviewable> extends selectionComponent<T> {
  
  @Input() items: T[];
  constructor(private injectedSelectionService:SelectedService<T>) {super(injectedSelectionService);}

  topN(items:T[],n:number):T[]{
    return items.slice(0,n);
    //return items.sort((i1,i2)=> i1.compare(i2)).slice(0,n);
  }
}
