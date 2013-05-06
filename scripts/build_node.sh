#!/bin/sh

./bin/lein_prefer deps
./bin/lein_prefer cljsbuild once release

echo "Finalizing mori.node.js"

(cat mori_rel.js; echo ";module.exports = this.mori;") > mori.node.js

echo "Deleting mori_rel.js"

rm mori_rel.js

echo "Build finished."
