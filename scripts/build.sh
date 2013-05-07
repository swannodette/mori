#!/bin/sh

./bin/lein_prefer deps
./bin/lein_prefer cljsbuild once release

echo "Finalizing mori.js"

(echo "(function() {"; cat mori.bare.js; echo "}).call(this);") > mori.js

echo "Finalizing mori.node.js"

(cat mori.bare.js; echo ";module.exports = this.mori;") > mori.node.js

echo "Deleting mori.bare.js"

rm mori.bare.js

echo "Build finished."
