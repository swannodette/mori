PS.js
====

A simple bridge to ClojureScript persistent data structures for JavaScript.

Build
----

Install Leiningen. Install lein-cljsbuild.

Grab dependencies:

```shell
lein deps
```

Build with:

```shell
lein cljsbuild once release
```

This will produce a file ps.js. You can include this like any other JavaScript library.

You can use it from your projects like so:

```javascript
var sum = function(a, b) {
  return a + b;
}
psjs.reduce(sum, psjs.vector(1, 2, 3, 4)); // => 10
```

Copyright (C) 2012 David Nolen

Distributed under the Eclipse Public License, the same as Clojure.
