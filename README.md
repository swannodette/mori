Mori
====

A simple bridge to ClojureScript's persistent data structures and supporting APIs for vanilla JavaScript. Pull requests welcome.

Getting it
----

You can download the latest prebuilt version of Mori from the [downloads](http://github.com/swannodette/mori/downloads) tab.

Caveats
----

Pre-pre-pre alpha. ClojureScript is constantly being improved, especially in terms of performance. That said, it's probably still already useful.

Build
----

Make a folder in the repo folder called checkouts, clone the [ClojureScript](http://github.com/clojure/clojurescript) repo into it.

Install [Leiningen](http://github.com/technomancy/leiningen).

Grab dependencies:

```shell
lein deps
```

Build with:

```shell
lein cljsbuild once release
```

This will produce a file mori.js. You can include this like any other JavaScript library.

Usage
----

You can use it from your projects like so:

```javascript
mori.into_array(mori.map(function(x) { return x+1;}, mori.vector(1,2,3,4,5)));
// => [2,3,4,5,6]
```

Efficient non-destructive updates!

```javascript
var v1 = mori.vector(1,2,3);
var v2 = mori.conj(v1, 4);
v1.toString(); // => '[1 2 3]'
v2.toString(); // => '[1 2 3 4]'
```

```javascript
var sum = function(a, b) {
  return a + b;
};
mori.reduce(sum, mori.vector(1, 2, 3, 4)); // => 10
```

Lazy sequences!

```javascript
var _ = mori;
_.into_array(_.interpose("foo", _.vector(1, 2, 3, 4)));
// => [1, "foo", 2, "foo", 3, "foo", 4]
```

Or if it's more your speed, use it from CoffeeScript!

```coffeescript
inc = (x) -> x+1  
r = mori.map inc, mori.vector(1,2,3,4,5)
mori.into_array r
```

Reducers
----

Mori includes the new Clojure reducers framework. Zero allocation collection operations FTW:

```javascript
var m = mori;
var a = [];

for(var i = 0; i < 100000; i++) {
  a.push(i);
}

// make it immutable
var v = m.into(m.vector(), a);

m.reduce(m.sum, 0, m.rmap(m.inc, m.rfilter(m.is_even, v)));
```

Pipelines
---------

```javascript
mori.pipeline(mori.vector(1,2,3),
              function(v) { return mori.conj(v,4) },
              function(v) { return mori.drop(2, v) });

// => [3 4]
```

Currying
--------

```javascript
mori.pipeline(mori.vector(1,2,3),
              mori.curry(mori.conj, 4),
              mori.curry(mori.conj, 5));

// => [1 2 3 4 5]
```

Partial Application
-------------------

```javascript
mori.pipeline(mori.vector(1,2,3),
              mori.curry(mori.conj, 4),
              mori.partial(mori.drop, 2));

// => (3 4)
```

Function Composition
--------------------

```javascript
var second = mori.comp(mori.first, mori.rest);

second(mori.vector(1,2,3));
// => 2
```

Copyright (C) 2012 David Nolen and contributors

Distributed under the Eclipse Public License, the same as Clojure.
