(defproject psjs "1.0.0-SNAPSHOT"
  :description "Persistent Data Structures for JavaScriopt"
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :dev-dependencies [[lein-cljsbuild "0.1.9"]]
  :cljsbuild {:builds {:release {:source-path "src"
                                 :compiler {:optimizations :advanced
                                            :output-to "ps.js"}}}})