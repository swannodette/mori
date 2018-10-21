#!/bin/sh

set -e

./bin/lein_prefer deps
./bin/lein_prefer cljsbuild once release

echo "Finalizing mori.js"

(cat support/wrapper.beg.txt; cat release/build/mori.base.js; cat release/build/mori.extra.js; cat release/build/mori.mutable.js; cat support/wrapper.end.txt) > mori.js

echo "Build finished."
