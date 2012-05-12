lein cljsbuild clean
lein cljsbuild once tmp
(echo "(function() {"; cat tmp.js; echo "}).call(this);") > mori.js
rm tmp.js
