;(function() {

    var m = mori;
    var a = [];

    function log(s) {
        if(typeof console != "undefined") {
            console.log(s);
        } else {
            print(s);
        }
    }

    for(var i = 0; i < 1000000; i++) {
        a.push(i);
    }

    // make it immutable
    var v = m.into(m.vector(), a);

    var mul3 = function(n) {
        return n*3;
    }

    function time(f, iters) {
        var el = 0,
            ret = null;

        for(var i = 0; i < iters; i++) {
            var s = new Date();
            ret = f();
            el += (new Date())-s;
        }
        
        log(ret + " " + (el/iters) +"ms");
    }

    time(function() {
        return m.reduce(m.sum, 0, m.rmap(m.inc, m.rmap(m.inc, m.rmap(m.inc, v))));
    }, 10);

    time(function() {
        return a.map(m.inc).map(m.inc).map(m.inc).reduce(function(a,b){return a+b;}, 0);
    }, 10);
})();
