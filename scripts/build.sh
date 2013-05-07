#!/bin/sh

./bin/lein_prefer deps
./bin/lein_prefer cljsbuild once release

echo "Finalizing mori.js"

(cat support/wrapper.beg.txt; cat mori.bare.js; cat support/wrapper.end.txt) > mori.js

echo "Deleting mori.bare.js"

rm mori.bare.js

echo "Build finished."
