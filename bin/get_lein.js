#!/usr/bin/env node

var https = require("https"),
    fs    = require("fs");

var lein_loc = __dirname + "/lein";

https.get("https://raw.github.com/technomancy/leiningen/stable/bin/lein", function(res) {
    var body = "";
    res.setEncoding("utf8");
    res.on("data", function (chunk) {
        body += chunk;
    });
    res.on("end", function () {
        fs.writeFileSync(lein_loc, body);
    });
}).on("error", function(e) {
    console.log("HTTP error: " + e.message);
});
