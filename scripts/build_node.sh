#!/bin/sh

./bin/lein_prefer deps
./bin/lein_prefer cljsbuild once release

echo "Finalizing mori.node.js"

(cat mori-rel.js; echo ";module.exports = this.mori;\n") > mori.node.js

echo "Deleting mori-rel.js"

rm mori-rel.js

echo "Build finished."
