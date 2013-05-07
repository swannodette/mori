lein cljsbuild clean
lein cljsbuild once release
$moribarejs = Get-Content mori.bare.js
ni "mori.js" -type file -force
ac "mori.js" "(function() {"
ac "mori.js" $moribarejs
ac "mori.js" "}).call(this);"
ni "mori.node.js" -type file -force
ac "mori.node.js" $moribarejs
ac "mori.node.js" ";module.exports = this.mori;"
rm mori.bare.js
