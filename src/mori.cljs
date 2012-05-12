(ns mori
  (:refer-clojure :exclude
   [count empty first rest conj cons nth last assoc dissoc
    get-in update-in assoc-in fnil disj pop peek hash get contains? empty? reverse
    take drop partition partition-by iterate into
    interpose interleave concat flatten
    keys vals
    map mapcat reduce reduce-kv filter remove some every? equiv
    range repeat repeatedly sort sort-by
    into-array
    partial comp
    list vector hash-map set sorted-set
    sum inc dec even? odd?])
  (:require [clojure.set :as set]
            [clojure.core.reducers :as reducers]))

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
(def ^:export get-in cljs.core/get-in)
(def ^:export update-in cljs.core/update-in)
(def ^:export assoc-in cljs.core/assoc-in)
(def ^:export fnil cljs.core/fnil)
(def ^:export disj cljs.core/disj)
(def ^:export pop cljs.core/pop)
(def ^:export peek cljs.core/peek)
(def ^:export hash cljs.core/hash)
(def ^:export get cljs.core/get)
(def ^:export has-key cljs.core/contains?)
(def ^:export is-empty cljs.core/empty?)
(def ^:export reverse cljs.core/reverse)
(def ^:export take cljs.core/take)
(def ^:export drop cljs.core/drop)
(def ^:export partition cljs.core/partition)
(def ^:export partition-by cljs.core/partition-by)
(def ^:export iterate cljs.core/iterate)
(def ^:export into cljs.core/into)
(def ^:export interpose cljs.core/interpose)
(def ^:export interleave cljs.core/interleave)
(def ^:export concat cljs.core/concat)
(def ^:export flatten cljs.core/flatten)
(def ^:export keys cljs.core/keys)
(def ^:export vals cljs.core/vals)
(def ^:export map cljs.core/map)
(def ^:export mapcat cljs.core/mapcat)
(def ^:export reduce cljs.core/reduce)
(def ^:export reduce-kv cljs.core/reduce-kv)
(def ^:export filter cljs.core/filter)
(def ^:export remove cljs.core/remove)
(def ^:export some cljs.core/some)
(def ^:export every cljs.core/every?)
(def ^:export equals cljs.core/=)
(def ^:export range cljs.core/range)
(def ^:export repeat cljs.core/repeat)
(def ^:export repeatedly cljs.core/repeatedly)
(def ^:export sort cljs.core/sort)
(def ^:export sort-by cljs.core/sort-by)
(def ^:export into-array cljs.core/into-array)

;; Reducers
(def ^:export rmap reducers/map)
(def ^:export rfilter reducers/filter)
(def ^:export rremove reducers/remove)
(def ^:export rtake reducers/take)
(def ^:export rtake-while reducers/take-while)
(def ^:export rdrop reducers/drop)
(def ^:export rflatten reducers/flatten)

(def ^:export list cljs.core/list)
(def ^:export vector cljs.core/vector)
(def ^:export hash-map cljs.core/hash-map)
(def ^:export set cljs.core/set)
(def ^:export sorted-set cljs.core/sorted-set)

;; Set ops
(def ^:export union set/union)
(def ^:export intersection set/intersection)
(def ^:export difference set/difference)
(def ^:export is-subset set/subset?)
(def ^:export is-superset set/superset?)

;; HOFs

(def ^:export partial cljs.core/partial)
(def ^:export comp cljs.core/comp)

(defn ^:export pipeline [& args]
  (reduce #(%2 %1) args))

(defn ^:export curry [fun & args]
  (fn [arg]
    (apply fun (cons arg args))))

;; Useful fns

(def ^:export sum (fn [s n] (+ s n)))
(def ^:export inc (fn [n] (+ n 1)))
(def ^:export dec (fn [n] (- n 1)))
(def ^:export is-even (fn [n] (zero? (mod n 2))))
(def ^:export is-odd (fn [n] (== (mod n 2) 1)))

(def ^:export each [xs f]
  (doseq [x (seq xs)]
    (f x)))