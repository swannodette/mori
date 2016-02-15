#!/usr/bin/env node

var wget = require('wgetjs');

wget({
    url: "https://raw.github.com/technomancy/leiningen/stable/bin/lein",
    dest: __dirname + "/lein",
}, function(err) {
    if (err) {
        console.log(err.message);
    }
});
