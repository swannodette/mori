#!/bin/sh

./bin/lein deps
./bin/lein cljsbuild once tmp

echo "Finalizing mori.js"

(echo "(function() {"; cat tmp.js; echo "}).call(this);") > mori.js

echo "Deleting tmp.js"

rm tmp.js

echo "Build finished."
