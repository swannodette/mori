(ns psjs
  (:refer-clojure :exclude
   [count empty first rest conj cons nth last assoc dissoc
    disj pop peek hash get contains? empty? reverse
    map reduce filter equiv
    range sort sort-by
    to-array
    list vector hash-map set sorted-set]))

(def ^:export count cljs.core/count)
(def ^:export empty cljs.core/empty)
(def ^:export first cljs.core/first)
(def ^:export rest cljs.core/rest)
(def ^:export conj cljs.core/conj)
(def ^:export cons cljs.core/cons)
(def ^:export nth cljs.core/nth)
(def ^:export last cljs.core/last)
(def ^:export assoc cljs.core/assoc)
(def ^:export dissoc cljs.core/dissoc)
(def ^:export disj cljs.core/disj)
(def ^:export pop cljs.core/pop)
(def ^:export peek cljs.core/peek)
(def ^:export hash cljs.core/hash)
(def ^:export get cljs.core/get)
(def ^:export hash-key cljs.core/contains?)
(def ^:export is-empty cljs.core/empty?)
(def ^:export reverse cljs.core/reverse)
(def ^:export map cljs.core/map)
(def ^:export reduce cljs.core/reduce)
(def ^:export filter cljs.core/filter)
(def ^:export equiv cljs.core/equiv)
(def ^:export range cljs.core/range)
(def ^:export sort cljs.core/sort)
(def ^:export sort cljs.core/sort-by)
(def ^:export to-array cljs.core/to-array)

(def ^:export list cljs.core/list)
(def ^:export vector cljs.core/vector)
(def ^:export hash-map cljs.core/hash-map)
(def ^:export set cljs.core/set)
(def ^:export set cljs.core/sorted-set)