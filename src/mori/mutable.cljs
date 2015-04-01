(ns mori.mutable
  (:refer-clojure :exclude
    [transient persistent! conj assoc dissoc pop disj])
  (:require-macros [mori.macros :refer [mori-export]]))

(mori-export thaw cljs.core/transient)
(mori-export freeze cljs.core/persistent!)
(mori-export conj cljs.core/conj!)
(mori-export assoc cljs.core/assoc!)
(mori-export dissoc cljs.core/dissoc!)
(mori-export pop cljs.core/pop!)
(mori-export disj cljs.core/disj!)

