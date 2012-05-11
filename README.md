Mori
====

A simple bridge to ClojureScript's persistent data structures and supporting APIs for vanilla JavaScript. Pull requests welcome.

Usage
----

You can download the latest prebuilt version of Mori from the downloads tab.

Build
----

Install [Leiningen](https://github.com/technomancy/leiningen).

Grab dependencies:

```shell
lein deps
```

Build with:

```shell
lein cljsbuild once release
```

This will produce a file mori.js. You can include this like any other JavaScript library.

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
_.into_array(_.interpose("foo", _.vector(1, 2, 3, 4)))
// => [1, "foo", 2, "foo", 3, "foo", 4]
```

Use it from CoffeeScript!

```coffeescript
inc = (x) -> x+1  
r = mori.map(inc, mori.vector(1,2,3,4,5))
mori.into_array(r)
```

## Pipelines

```javascript
mori.pipeline(mori.vector(1,2,3),
              function(v) { return mori.conj(v,4) },
              function(v) { return mori.drop(2, v) });

// => [3 4]
```

## Currying

```javascript
mori.pipeline(mori.vector(1,2,3),
              mori.curry(mori.conj, 4),
              mori.curry(mori.conj, 5));

// => [1 2 3 4 5]
```

## Partial Application

```javascript
mori.pipeline(mori.vector(1,2,3),
              mori.curry(mori.conj, 4),
              mori.partial(mori.drop, 2));

// => (3 4)
```

## Function Composition

```javascript
var second = mori.comp(mori.first, mori.rest);

second(mori.vector(1,2,3));
// => 2
```

Copyright (C) 2012 David Nolen

Distributed under the Eclipse Public License, the same as Clojure.
