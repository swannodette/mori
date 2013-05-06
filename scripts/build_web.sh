#!/bin/sh

./bin/lein_prefer deps
./bin/lein_prefer cljsbuild once tmp

echo "Finalizing mori.js"

(echo "(function() {"; cat tmp.js; echo "}).call(this);") > mori.js

echo "Deleting tmp.js"

rm tmp.js

echo "Build finished."
