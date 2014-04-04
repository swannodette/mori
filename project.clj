(defproject mori "0.2.6"
  :description "Persistent Data Structures for JavaScript"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2197"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild
  {:builds
    [{:source-paths ["src"],
      :id "dev",
      :compiler
      {:output-to "mori.dev.js",
       :optimizations :simple
       :output-wrapper false
       :pretty-print true}}
     {:source-paths ["src"],
      :id "release",
      :compiler
      {:output-to "mori.bare.js",
       :optimizations :advanced
       :output-wrapper false
       :pretty-print false}}]})
