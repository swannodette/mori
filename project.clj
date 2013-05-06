(defproject mori "0.2.0-SNAPSHOT"
  :description "Persistent Data Structures for JavaScript"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1803"]]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :cljsbuild
  {:builds
    [{:source-paths ["src"],
       :id "tmp",
       :compiler
       {:output-to "tmp.js",
        :optimizations :advanced}}
      {:source-paths ["src"],
        :id "dev",
        :compiler
        {:output-to "mori_dev.js",
         :optimizations :whitespace}}
      {:source-paths ["src"],
        :id "release",
        :compiler
        {:output-to "mori_rel.js",
         :optimizations :advanced
         :pretty-print false}}]})
