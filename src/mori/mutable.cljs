(ns mori.mutable
  (:refer-clojure :exclude
    [transient persistent! conj assoc dissoc pop disj])
  (:require-macros [mori.macros :refer [mori-export]]))

(mori-export _thaw cljs.core/transient)
(mori-export _freeze cljs.core/persistent!)
(mori-export _conj cljs.core/conj!)
(mori-export _assoc cljs.core/assoc!)
(mori-export _dissoc cljs.core/dissoc!)
(mori-export _pop cljs.core/pop!)
(mori-export _disj cljs.core/disj!)

