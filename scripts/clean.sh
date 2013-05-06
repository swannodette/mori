#!/bin/sh

rm -f mori.node.js mori.js

./bin/lein_prefer cljsbuild clean
