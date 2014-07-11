(defproject mori "0.2.8"
  :description "Persistent Data Structures for JavaScript"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2268"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]

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
