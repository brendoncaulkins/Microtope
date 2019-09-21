import { Injectable } from '@angular/core';

import { Observable, BehaviorSubject } from 'rxjs';

/*
Subscriptionbehavior taken from
https://stackoverflow.com/questions/53377973/angular-6-update-a-value-in-my-component-when-she-change-in-my-service
*/

/**
 * https://stackoverflow.com/questions/53377973/angular-6-update-a-value-in-my-component-when-she-change-in-my-service
 * sucks dicks and has no idea
 */


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
