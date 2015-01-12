(ns bonsai
  (:refer-clojure :exclude
   [count empty conj find nth assoc dissoc disj pop peek hash get
    empty? reverse into merge subvec keys select-keys vals
    equiv range sort sort-by into-array
    list vector array-map hash-map set sorted-set
    sorted-set-by sorted-map sorted-map-by
    subseq compare meta with-meta vary-meta]))

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
(def ^:export array-map cljs.core/array-map)
(def ^:export hash-map cljs.core/hash-map)
(def ^:export set cljs.core/set)
(def ^:export sorted-set cljs.core/sorted-set)
(def ^:export sorted-set-by cljs.core/sorted-set-by)
(def ^:export sorted-map cljs.core/sorted-map)
(def ^:export sorted-map-by cljs.core/sorted-map-by)
(def ^:export subseq cljs.core/subseq)
(def ^:export compare cljs.core/compare)
(def ^:export meta cljs.core/meta)
(def ^:export with-meta cljs.core/with-meta)
(def ^:export vary-meta cljs.core/vary-meta)
