(ns mori
  (:require-macros
   [mori.macros :refer [mori-export]])
  (:refer-clojure :exclude
   [count empty conj find nth assoc dissoc disj pop peek get
    empty? reverse into merge subvec keys vals
    equiv sort sort-by
    vector vec array-map hash-map set compare]))

(mori-export count cljs.core/count)
(mori-export empty cljs.core/empty)
(mori-export conj cljs.core/conj)
(mori-export find cljs.core/find)
(mori-export nth cljs.core/nth)
(mori-export assoc cljs.core/assoc)
(mori-export dissoc cljs.core/dissoc)
(mori-export disj cljs.core/disj)
(mori-export pop cljs.core/pop)
(mori-export peek cljs.core/peek)
(mori-export get cljs.core/get)
(mori-export isEmpty cljs.core/empty?)
(mori-export reverse cljs.core/reverse)
(mori-export into cljs.core/into)
(mori-export merge cljs.core/merge)
(mori-export subvec cljs.core/subvec)
(mori-export keys cljs.core/keys)
(mori-export vals cljs.core/vals)
(mori-export equiv cljs.core/==)
(mori-export sort cljs.core/sort)
(mori-export sortBy cljs.core/sort-by)
(mori-export vector cljs.core/vector)
(mori-export vec cljs.core/vec)
(mori-export Vector cljs.core/PersistentVector)
(mori-export hashMap cljs.core/array-map)
(mori-export ArrayMap cljs.core/PersistentArrayMap)
(defn ^:export arrayMapFromArray [arr no-clone no-check]
  (cljs.core/PersistentArrayMap.fromArray arr no-clone no-check))
(defn ^:export arrayMapUnwrap [m]
  (if (instance? cljs.core/PersistentArrayMap m)
    (.-arr m)
    (throw (js/Error. "Can only unwrap array maps"))))
(mori-export Map cljs.core/PersistentHashMap)
(mori-export set cljs.core/set)
(mori-export Set cljs.core/PersistentHashSet)
(mori-export compare cljs.core/compare)
