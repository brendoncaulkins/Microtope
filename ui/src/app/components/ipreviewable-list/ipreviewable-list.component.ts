import {Component, Input} from '@angular/core';
import {Observable} from 'rxjs';

import {IPreviewable} from 'src/app/models/IPreviewable';
import {SelectedService} from 'src/app/services/selected.service';

@Component({
  selector: 'app-ipreviewable-list',
  templateUrl: './ipreviewable-list.component.html',
  styleUrls: ['./ipreviewable-list.component.css']
})
export class IPreviewableListComponent {

  @Input() items: Observable<IPreviewable[]>;

  constructor(public selection: SelectedService<IPreviewable>) {}

  onSelect(item: IPreviewable): void {
    this.selection.select(item);
  }

}
