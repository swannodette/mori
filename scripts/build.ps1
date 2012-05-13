lein cljsbuild clean
lein cljsbuild once tmp
ni "mori.js" -type file
ac "mori.js" "(function() {"
$tmpjs = Get-Content tmp.js
ac "mori.js" $tmpjs
ac "mori.js" "}).call(this);"
rm tmp.js
