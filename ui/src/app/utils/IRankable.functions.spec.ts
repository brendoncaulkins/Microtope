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
      const input:Team[] = [];
        
      const output = topN(input,5);
      
      expect(output).toBe([]);
    });
    
  });
  