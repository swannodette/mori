lein cljsbuild clean
lein cljsbuild once tmp
ni "mori.js" -type file -force
ac "mori.js" "(function() {"
$tmpjs = Get-Content tmp.js
ac "mori.js" $tmpjs
ac "mori.js" "}).call(this);"
rm tmp.js
lein cljsbuild once release
ni "mori.node.js" -type file -force
$reljs = Get-Content mori_rel.js
ac "mori.node.js" $reljs
ac "mori.node.js" ";module.exports = this.mori;"
rm mori_rel.js
