(ns mori.mutable
  (:refer-clojure :exclude
    [transient persistent! conj assoc dissoc pop disj]))

(def ^:export thaw cljs.core/transient)
(def ^:export freeze cljs.core/persistent!)

(defn ^:export conj1 [^not-native coll x]
  (cljs.core/-conj! coll x))
(def ^:export conj cljs.core/conj!)

(def ^:export assoc cljs.core/assoc!)
(def ^:export dissoc cljs.core/dissoc!)
(def ^:export pop cljs.core/pop!)
(def ^:export disj cljs.core/disj!)

