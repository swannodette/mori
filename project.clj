(defproject mori "0.5.0-SNAPSHOT"
  :description "Persistent Data Structures for JavaScript"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3169"]]

  :plugins [[lein-cljsbuild "1.0.5"]]

  :clean-targets ["dev" "release" "target"]
  
  :cljsbuild
  {:builds
    [;; mori
     {:source-paths ["src"],
      :id "dev",
      :compiler
      {:output-to      "dev/mori.dev.js",
       :output-dir     "dev/"
       :optimizations  :simple
       :cache-analysis true
       :output-wrapper false
       :pretty-print   true}}

     {:source-paths ["src"],
      :id "release"
      :compiler
      {:optimizations  :advanced
       :output-dir     "release"
       :output-wrapper false
       :pretty-print   false
       :modules
       {:cljs-base {:output-to "release/build/mori.base.js"}
        :mori      {:entries #{mori}
                    :output-to "release/build/mori.core.js"}
        :mutable   {:entries #{mori.mutable}
                    :depends-on #{:mori}
                    :output-to "release/build/mori.mutable.js"}
        :extra     {:entries #{mori.extra}
                    :depends-on #{:mori}
                    :output-to "release/build/mori.extra.js"}}}}]})
