var mori = require("../");

describe("Map", function () {

    it("demonstrates mapping a function over a vector", function () {

        var inc = function (n) { return n + 1; },
            out_arr = mori.into_array(mori.map(inc, mori.vector(1,2,3,4,5)));

        expect(out_arr).toEqual([2,3,4,5,6]);

    });

});

describe("Non-destructive update", function () {

    it("demonstrates a non-destructive collection update", function () {

        var v1 = mori.vector(1,2,3),
            v2 = mori.conj(v1, 4);

        expect(JSON.stringify(mori.into_array(v1))).toEqual("[1,2,3]");
        expect(JSON.stringify(mori.into_array(v2))).toEqual("[1,2,3,4]");

    });

});

describe("Reduce", function () {

    it("demonstrates functional reduction on a vector", function () {

        var sum = function(a, b) {
            return a + b;
        };

        var reduced = mori.reduce(sum, mori.vector(1, 2, 3, 4));

        expect(reduced).toEqual(10);

    });

});

describe("Lazy sequences", function () {

    it("demonstrates interposition on a lazy sequence", function () {

        var _ = mori;

        var interposed = _.into_array(_.interpose("foo", _.vector(1, 2, 3, 4)));

        expect(interposed).toEqual([1, "foo", 2, "foo", 3, "foo", 4]);

    });

});

describe("Reducers", function () {

    it("demonstrates reducer-functions usage", function () {

        var m = mori,
            a = [];

        for(var i = 0; i < 100000; i++) {
            a.push(i);
        }

        var v = m.into(m.vector(), a);

        var mul3 = function(n) {
            return n*3;
        };

        // function time(f) {
        //     var s = new Date();
        //     f();
        //     console.log(((new Date())-s)+"ms");
        // }

        var res1 = m.reduce(m.sum, 0, m.rmap(m.inc, m.rfilter(m.is_even, m.rmap(mul3, v)))),
            res2 = a.map(mul3).filter(m.is_even).map(m.inc).reduce(m.sum);

        expect(res1).toEqual(7499900000);
        expect(res2).toEqual(res1);

    });

});

describe("Pipelines", function () {

    it("demonstrates function pipelining", function () {

        var pipe_res = mori.pipeline(mori.vector(1,2,3),
                                     function(v) { return mori.conj(v,4); },
                                     function(v) { return mori.drop(2, v); });

        expect(mori.into_array(pipe_res)).toEqual([3,4]);

    });

});

describe("Currying", function () {

    it("demonstrates function currying", function () {

        var cur_res = mori.pipeline(mori.vector(1,2,3),
                                    mori.curry(mori.conj, 4),
                                    mori.curry(mori.conj, 5));;

        expect(mori.into_array(cur_res)).toEqual([1,2,3,4,5]);

    });

});

describe("Partial application", function () {

    it("demonstrates partial function application", function () {

        var par_res = mori.pipeline(mori.vector(1,2,3),
                                    mori.curry(mori.conj, 4),
                                    mori.partial(mori.drop, 2));

        expect(mori.into_array(par_res)).toEqual([3, 4]);

    });

});


describe("Function composition", function () {

    it("demonstrates function composition", function () {

        var second = mori.comp(mori.first, mori.rest);

        var sec_res = second(mori.vector(1,2,3));

        expect(sec_res).toEqual(2);

    });

});

describe("Juxtaposition", function () {

    it("demonstrates function juxtaposition", function () {

        var pos_and_neg = mori.juxt(mori.identity, function (v) { return -v; });

        var pn_res = pos_and_neg(1);

        expect(mori.into_array(pn_res)).toEqual([1,-1]);

        var knit_res = mori.knit(mori.inc, mori.dec)(pos_and_neg(1));

        expect(mori.into_array(knit_res)).toEqual([2,-2]);

    });

});

describe("Conversion utilities", function () {

    it("demonstrates conversion from clojurescript values to javascript objects, and vice versa", function () {

        var js_obj  = { a: 1, b: "two" },
            js_arr  = [1, 2, 3],
            clj_map = mori.hash_map("a", 1, "b", "two"),
            clj_map_keywordized = mori.hash_map(mori.keyword("a"), 1, mori.keyword("b"), "two"),
            clj_vec = mori.vector(1, 2, 3);


        expect(mori.equals(mori.js_to_clj(js_obj), clj_map)).toBe(true);
        expect(mori.equals(mori.js_to_clj(js_obj,true), clj_map_keywordized)).toBe(true);

        expect(mori.equals(mori.js_to_clj(js_arr), clj_vec)).toBe(true);

        expect(mori.clj_to_js(clj_map)).toEqual(js_obj);

        expect(mori.clj_to_js(clj_vec)).toEqual(js_arr);

    });

});
