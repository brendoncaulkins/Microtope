import { Injectable } from '@angular/core';

import { Observable, BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SelectedService<T> {
  private subject$$ = new BehaviorSubject<T>(null);
  selected$: Observable<T> = this.subject$$;

  public select(selected: T): void {
    this.subject$$.next(selected);
  }

  public deselect(): void {
    this.subject$$.next(null);
  }

}
