import {TestBed} from '@angular/core/testing';
import {SelectedService} from '../../services/selected.service';
import {TopRankableComponent} from './top-rankable.component';

describe(`${TopRankableComponent.constructor.name}`, () => {
  beforeAll(() => {
    const spy = jasmine.createSpyObj('select', 'selected$');
    TestBed.configureTestingModule({
      declarations: [TopRankableComponent],
      providers: [
        {provide: SelectedService, useValue: spy}
      ]
    });
  });

  it('should return empty Array if given empty Array', () => {
    
    const fixture = TestBed.createComponent(TopRankableComponent);
    const comp = fixture.componentInstance;

    // Simulate "Input"
    comp.items = [];

    // Trigger change detection, this is where ngOninit runs
    fixture.detectChanges();

    expect(comp.sortedItems).toBe([]);
  });
});
