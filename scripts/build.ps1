lein deps
lein cljsbuild clean
lein cljsbuild once release
$moribarejs = Get-Content mori.bare.js
$wrapperbeg = Get-Content support/wrapper.beg.txt
$wrapperend = Get-Content support/wrapper.end.txt
ni "mori.js" -type file -force
ac "mori.js" $wrapperbeg
ac "mori.js" $moribarejs
ac "mori.js" $wrapperend
rm mori.bare.js
