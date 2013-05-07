(function (definition) {
    // This file will function properly as a <script> tag, or a module
    // using CommonJS and NodeJS or RequireJS module formats. In
    // Common/Node/RequireJS, the module exports the mori API and when
    // executed as a simple <script>, it creates a mori global
    // instead.

    // Wrapper gratefully adapted from:
    // https://github.com/kriskowal/q/blob/master/q.js

    // CommonJS
    if (typeof exports === "object") {
        module.exports = definition();

    // RequireJS
    } else if (typeof define === "function" && define.amd) {
        define(definition);

    // <script>
    } else {
        mori = definition();
    }
})(function () {
    return function () {

        var REPLACE_WITH_MORI_BARE_JS;

        ;return this.mori;

    }.call({});

});
