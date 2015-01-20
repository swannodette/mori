(ns bonsai
  (:refer-clojure :exclude
   [count empty conj find nth assoc dissoc disj pop peek hash get
    empty? reverse into merge subvec keys select-keys vals
    equiv range sort sort-by into-array
    list vector array-map hash-map set compare]))

(def ^:export count cljs.core/count)
(def ^:export empty cljs.core/empty)
(def ^:export conj cljs.core/conj)
(def ^:export find cljs.core/find)
(def ^:export nth cljs.core/nth)
(def ^:export assoc cljs.core/assoc)
(def ^:export dissoc cljs.core/dissoc)
(def ^:export disj cljs.core/disj)
(def ^:export pop cljs.core/pop)
(def ^:export peek cljs.core/peek)
(def ^:export hash cljs.core/hash)
(def ^:export get cljs.core/get)
(def ^:export empty? cljs.core/empty?)
(def ^:export reverse cljs.core/reverse)
(def ^:export into cljs.core/into)
(def ^:export merge cljs.core/merge)
(def ^:export subvec cljs.core/subvec)
(def ^:export keys cljs.core/keys)
(def ^:export select-keys cljs.core/select-keys)
(def ^:export vals cljs.core/vals)
(def ^:export equiv cljs.core/==)
(def ^:export range cljs.core/range)
(def ^:export sort cljs.core/sort)
(def ^:export sort-by cljs.core/sort-by)
(def ^:export into-array cljs.core/into-array)
(def ^:export list cljs.core/list)
(def ^:export vector cljs.core/vector)
(def ^:export hash-map cljs.core/hash-map)
(def ^:export set cljs.core/set)
(def ^:export compare cljs.core/compare)

