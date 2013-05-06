#!/bin/sh

./bin/lein deps
./bin/lein cljsbuild once release

echo "Finalizing mori.node.js"

(cat mori-rel.js; echo ";module.exports = this.mori;\n") > mori.node.js

echo "Deleting mori-rel.js"

rm mori-rel.js

echo "Build finished."
