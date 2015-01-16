// lein cljsbuild once release
// d8 node_modules/immutable/dist/immutable.min.js mori.bare.js ./bench/mut_perf.js
// jsc node_modules/immutable/dist/immutable.min.js mori.bare.js ./bench/mut_perf.js

;(function() {

    function sum(a,b) {
        return a+b;
    }    

    var m = mori;
    var runs = [];

    for(var j = 0; j < 10; j++) {
        var s = new Date();
        var arr = [];
        for(var i = 0; i < 1000000; i++) {
            if(i % 32 == 0) {
                arr.push(Math.random());
            } else {
                arr.push(i);
            }
        }
        var el = (new Date())-s;
        runs.push(el);
        print("Array push " + arr.length + " items " + el + " " + arr.reduce(sum, 0));
    }
    print(runs.reduce(sum,0)/10);

    // runs = [];
    // for(var j = 0; j < 10; j++) {
    //     s = new Date();
    //     var v = m.vector();
    //     for(var i = 0; i < 1000000; i++) {
    //         if(i % 32 === 0) {
    //             v = m.conj1(v, Math.random());
    //         } else {
    //             v = m.conj1(v, i);
    //         }
    //     }
    //     var el = (new Date())-s;
    //     runs.push(el);
    //     print("Persistent vector conj " + m.count(v) + " items " + el + " " + m.reduce(sum,0,v));
    // }
    // print(runs.reduce(sum,0)/10);

    runs = [];
    for(var j = 0; j < 10; j++) {
        s = new Date();
        var mv = m.mutable.thaw(m.vector());
        for(var i = 0; i < 1000000; i++) {
            if(i % 32 === 0) {
                mv = m.mutable.conj.f2(mv, Math.random());
            } else {
                mv = m.mutable.conj.f2(mv, i);
            }
        }
        var v = m.mutable.freeze(mv);
        var el = (new Date())-s;
        runs.push(el);
        print("Mutable vector conj " + m.count(v) + " items " + el + " " + m.reduce(sum,0,v));
    }
    print(runs.reduce(sum,0)/10);

    /*
    runs = [];
    for(var j = 0; j < 10; j++) {
        s = new Date();
        var l = Immutable.List().withMutations(function(ml) {
            for(var i = 0; i < 1000000; i++) {
                if(i % 32 === 0) {
                    ml.push(Math.random());
                } else {
                    ml.push(i);
                }
            }
        });
        var el = (new Date())-s;
        runs.push(el);
        print("Mutable list push " + l.size + " items " + el + " " + l.reduce(sum, 0));
    }
    print(runs.reduce(sum,0)/10);
    */

})();

