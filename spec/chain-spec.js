var mori = require("../mori")
  , c = mori.chain
  , _ = c._;

describe("ChainedMap", function () {

    it("demonstrates mapping a function over a vector", function () {

        var inc = function (n) { return n + 1; },
            outArr = _([1,2,3,4,5]).map(inc).out();

      

      expect(outArr).toEqual([2,3,4,5,6]);

    });

});

describe("Non-destructive update", function () {

    it("demonstrates a non-destructive collection update", function () {

      var v1 = _([1,2,3]),
          v2 = v1.conj(4);
      
      
      expect(JSON.stringify(v1.out())).toEqual("[1,2,3]");
      expect(JSON.stringify(v2.out())).toEqual("[1,2,3,4]");

    });

});

describe("Reduce", function () {

    it("demonstrates functional reduction on a vector", function () {

        var sum = function(a, b) {
            return a + b;
        };

      var reduced = _([1, 2, 3, 4]).reduce(sum, 0).out();

        expect(reduced).toEqual(10);

    });

});

describe("Lazy sequences", function () {

    it("demonstrates interposition on a lazy sequence", function () {

        var interposed = _([1, 2, 3, 4]).interpose("foo").out();

        expect(interposed).toEqual([1, "foo", 2, "foo", 3, "foo", 4]);

    });

});

describe("Conversion utilities", function () {

    it("demonstrates conversion from clojurescript values to javascript objects, and vice versa", function () {

        var jsObj  = { a: 1, b: "two" },
            jsArr  = [1, 2, 3],
            cljMap = _({a: 1, b: "two"}),
            cljMapKeywordized = mori.hashMap(mori.keyword("a"), 1, mori.keyword("b"), "two"),
            cljVec = _([1, 2, 3]);
      
        expect(_(jsObj).is(cljMap)).toBe(true);
        expect(_(jsObj,true).is(cljMapKeywordized)).toBe(true);
        expect(_(jsObj).is(jsObj)).toBe(false);

        expect(_(jsArr).is(cljVec)).toBe(true);
      
        expect(_(cljMap).out()).toEqual(jsObj);

        expect(_(cljVec).out()).toEqual(jsArr);

        expect(_(jsArr).isVector()).toBe(true);

    });

});

describe("Distinct", function() {
  it("demonstrates function `distinct`", function() {

    var vec = _([1,1,1,1,2,2,3,4,5,6,6]);

    var distinctVector = vec.distinct();

    expect(distinctVector.out()).toEqual([1,2,3,4,5,6]);

  });

});

describe("Predicates", function() {
  it("demonstrates predicate usage", function() {

    expect(_([]).isEmpty()).toBe(true);
    expect(_([1]).isEmpty()).toBe(false);
    
    expect(_([]).isList()).toBe(false);

    expect(_([1,2]).toSeq().isSeq()).toBe(true);
    expect(_([]).toSeq().isEmpty()).toBe(true);

    expect(_([1,2]).isVector()).toBe(true);
    expect(_([1,2]).toSet().isVector()).toBe(false);

    expect(_({a: 1, b: "two"}).isMap()).toBe(true);
    expect(_([1,2]).isMap()).toBe(false);

    expect(_([1,2,2]).toSet().isSet()).toBe(true);
    expect(_([]).isSet()).toBe(false);
    
    expect(_([1,2]).isCollection()).toBe(true);
    expect(_(1).isCollection()).toBe(false);

    expect(_([1,2]).isSequential()).toBe(true);
    expect(_(1).isSequential()).toBe(false);

    expect(_([1,2]).isAssociative()).toBe(true);
    expect(_([]).map(mori.identity).isAssociative()).toBe(false);

    expect(_([]).isCounted()).toBe(true);
    expect(_(1).isCounted()).toBe(false);

    expect(_([]).isIndexed()).toBe(true);
    expect(_([]).map(mori.identity).isIndexed()).toBe(false);

    expect(_([]).isReduceable()).toBe(true);
    expect(_(1).isReduceable()).toBe(false);
    
    expect(_([]).isSeqable()).toBe(true);
    expect(_([1]).isSeqable()).toBe(true);

    expect(_([]).isReversible()).toBe(true);
    expect(_(1).isReversible()).toBe(false);
    
    expect(_([]).isChainable()).toBe(true);
    
  });

});

