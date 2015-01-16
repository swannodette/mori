(defproject mori "0.3.2"
  :description "Persistent Data Structures for JavaScript"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2665"]]

  :plugins [[lein-cljsbuild "1.0.4"]
            [cider/cider-nrepl "0.8.1"]]

  :cljsbuild
  {:builds
    [;; mori
     {:source-paths ["src/mori"],
      :id "dev",
      :compiler
      {:output-to "mori.dev.js",
       :output-dir "out-mori-dev"
       :optimizations :simple
       :cache-analysis true
       :output-wrapper false
       :pretty-print true}}

     {:source-paths ["src/mori"],
      :id "release",
      :compiler
      {:output-to "mori.bare.js",
       :output-dir "out-mori-adv"
       :optimizations :advanced
       :output-wrapper false
       :pretty-print false}}

     ;; bonsai
     {:source-paths ["src/bonsai"],
      :id "bonsai-release",
      :compiler
      {:output-to "bonsai.bare.js",
       :output-dir "out-bonsai-adv"
       :optimizations :advanced
       :output-wrapper false
       :pretty-print false}}]})
