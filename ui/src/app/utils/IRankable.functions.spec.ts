import {IRankable} from "../models/IRankable";
import {Team} from "../models/Team.model";
import {compare,topN} from "./IRankable.functions";


describe(`IRankable Compare`, () => {
  
  it('should return 0 if both are empty', () => {
    const first:IRankable = {};
    const second: IRankable = {};
    
    const result = compare(first,second);

    expect(result).toBe(0);
  });

  it('should return 1 if first has more coins', () => {
    const first:IRankable = {
        coins : 10
    };
    const second: IRankable = {
        coins : 5
    };
    
    const result = compare(first,second);

    expect(result).toBe(1);
  });

  it('should return -1 if second has more coins', () => {
    const first:IRankable = {
        coins : 5
    };
    const second: IRankable = {
        coins : 10
    };
    
    const result = compare(first,second);

    expect(result).toBe(-1);
  });

  it('should return 1 if first has coins second has not', () => {
    const first:IRankable = {
        coins : 10
    };
    const second: IRankable = {};
    
    const result = compare(first,second);

    expect(result).toBe(1);
  });

  it('should return -1 if second coins and first has not', () => {
    const first:IRankable = {};
    const second: IRankable = {
        coins : 10
    };
    
    const result = compare(first,second);

    expect(result).toBe(-1);
  });

  it('should return 0 if both have same coins but no steps', () => {
    const first:IRankable = {
        coins : 10
    };
    const second: IRankable = {
        coins : 10
    };
    
    const result = compare(first,second);

    expect(result).toBe(0);
  });


  it('should return 1 if both have same coins but first has more steps', () => {
    const first:IRankable = {
        coins : 10,
        steps: 101
    };
    const second: IRankable = {
        coins : 10,
        steps: 100
    };
    
    const result = compare(first,second);

    expect(result).toBe(1);
  });

  it('should return -1 if both have same coins but second has more steps', () => {
    const first:IRankable = {
        coins : 10,
        steps: 100
    };
    const second: IRankable = {
        coins : 10,
        steps: 101
    };
    
    const result = compare(first,second);

    expect(result).toBe(-1);
  });
  
  it('should return 0 if both have same coins and steps', () => {
    const first:IRankable = {
        coins : 10,
        steps: 100
    };
    const second: IRankable = {
        coins : 10,
        steps: 100
    };
    
    const result = compare(first,second);

    expect(result).toBe(0);
  });


  it('should return 1 if both have same coins but first has steps and second has not', () => {
    const first:IRankable = {
        coins : 10,
        steps: 101
    };
    const second: IRankable = {
        coins : 10
    };
    
    const result = compare(first,second);

    expect(result).toBe(1);
  });

  it('should return 1 if both have same coins but first has steps and second has not', () => {
    const first:IRankable = {
        coins : 10
    };
    const second: IRankable = {
        coins : 10,
        steps: 101
    };
    
    const result = compare(first,second);

    expect(result).toBe(-1);
  });

  it('should return 0 if object is compared to itself', () => {
    const first:IRankable = {
        coins : 10,
        steps: 100
    };
    
    const result = compare(first,first);

    expect(result).toBe(0);
  });

});


describe(`IRankable topN`, () => {
  
    it('should return empty Array if given empty Array', () => {
      const toPick:IRankable[] = [];
        
      const picked = topN(toPick,5);
      
      expect(picked).toEqual([]);
    });
    
    it('should return empty Array if asked for Top0', () => {
        const toPick:IRankable[] = [{coins:1}];
          
        const picked = topN(toPick,0);
        
        expect(picked).toEqual([]);
    });

    it('should return Array of one when asked for Top2 on array of size one', () => {
        const toPick:IRankable[] = [{coins:1}];
          
        const picked = topN(toPick,2);
        
        expect(picked).toEqual(toPick);
    });

    it('should return Array of size 3 when asked for Top3 on array of size 4', () => {
        const toPick:IRankable[] = [{coins:1},{coins:2},{coins:3},{coins:4}];
          
        const picked = topN(toPick,3);
        
        expect(picked.length).toBe(3);
    });

    it('should return the array if array was already sorted', () => {
        const toPick:IRankable[] = [{coins:3},{coins:2},{coins:1}];
          
        const picked = topN(toPick,toPick.length);
        
        expect(picked).toEqual(toPick);
    });

    it('should return the biggest value with top1', () => {
        const toPick:IRankable[] = [{coins:1},{coins:2},{coins:3}];
          
        const picked = topN(toPick,1);
        
        expect(picked).toEqual([{coins:3}]);
    });

    it('should return a sorted array', () => {
        const toPick:IRankable[] = [{coins:1},{coins:2},{coins:3}];
          
        const picked = topN(toPick,toPick.length);
        
        expect(picked).toEqual([{coins:3},{coins:2},{coins:1}]);
    });

    it('should not sort the inputarray', () => {
        const toPick:IRankable[] = [{coins:1},{coins:2},{coins:3}];
          
        topN(toPick,toPick.length);
        
        expect(toPick).toEqual([{coins:1},{coins:2},{coins:3}]);
    });
    
  });
  