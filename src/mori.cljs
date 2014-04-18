(ns mori
  (:refer-clojure :exclude
   [count distinct empty first rest seq conj cons find nth last assoc dissoc
    get-in update-in assoc-in fnil disj pop peek hash get empty? reverse
    take drop partition partition-by iterate into merge subvec
    take-while drop-while group-by
    interpose interleave concat flatten
    keys vals
    prim-seq
    map mapcat reduce reduce-kv filter remove some every? equiv
    range repeat repeatedly sort sort-by
    into-array
    partial comp juxt
    identity constantly
    list vector array-map hash-map zipmap set sorted-set keyword
    sorted-set-by sorted-map sorted-map-by
    sum inc dec even? odd? subseq
    apply])
  (:use-macros [mori.macros :only [make-inspectable]])
  (:require [clojure.set :as set]
            [clojure.core.reducers :as reducers]))

(def ^:export apply cljs.core/apply)
(def ^:export count cljs.core/count)
(def ^:export distinct cljs.core/distinct)
(def ^:export empty cljs.core/empty)
(def ^:export first cljs.core/first)
(def ^:export rest cljs.core/rest)
(def ^:export seq cljs.core/seq)
(def ^:export conj cljs.core/conj)
(def ^:export cons cljs.core/cons)
(def ^:export find cljs.core/find)
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
(def ^:export merge cljs.core/merge)
(def ^:export subvec cljs.core/subvec)
(def ^:export take-while cljs.core/take-while)
(def ^:export drop-while cljs.core/drop-while)
(def ^:export group-by cljs.core/group-by)
(def ^:export interpose cljs.core/interpose)
(def ^:export interleave cljs.core/interleave)
(def ^:export concat cljs.core/concat)

(defn sequential-or-array? [x]
  (or (array? x)
      (sequential? x)))

(defn ^:export flatten [x]
  (cljs.core/filter #(not (sequential-or-array? %))
    (cljs.core/rest (tree-seq sequential-or-array? seq x))))

(def ^:export keys cljs.core/keys)
(def ^:export vals cljs.core/vals)
(def ^:export prim-seq cljs.core/prim-seq)
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
(def ^:export subseq cljs.core/subseq)

;; Reducers
(def ^:export rmap reducers/map)
(def ^:export rfilter reducers/filter)
(def ^:export rremove reducers/remove)
(def ^:export rtake reducers/take)
(def ^:export rtake-while reducers/take-while)
(def ^:export rdrop reducers/drop)
(def ^:export rflatten reducers/flatten)

;; constructors

(def ^:export list cljs.core/list)
(def ^:export vector cljs.core/vector)
(def ^:export array-map cljs.core/array-map)
(def ^:export hash-map cljs.core/hash-map)
(def ^:export set cljs.core/set)
(def ^:export sorted-set cljs.core/sorted-set)
(def ^:export sorted-set-by cljs.core/sorted-set-by)
(def ^:export sorted-map cljs.core/sorted-map)
(def ^:export sorted-map-by cljs.core/sorted-map-by)
(def ^:export queue (fn [& args] (into cljs.core.PersistentQueue.EMPTY args)))

(def ^:export keyword cljs.core/keyword)

(def ^:export zipmap cljs.core/zipmap)

;; Predicates
(def ^:export is-list cljs.core/list?)
(def ^:export is-seq cljs.core/seq?)
(def ^:export is-vector cljs.core/vector?)
(def ^:export is-map cljs.core/map?)
(def ^:export is-set cljs.core/set?)

(def ^:export is-keyword cljs.core/keyword?)

(def ^:export is-collection cljs.core/coll?)
(def ^:export is-sequential cljs.core/sequential?)
(def ^:export is-associative cljs.core/associative?)
(def ^:export is-counted cljs.core/counted?)
(def ^:export is-indexed cljs.core/indexed?)
(def ^:export is-reduceable cljs.core/reduceable?)
(def ^:export is-seqable cljs.core/seqable?)
(def ^:export is-reversible cljs.core/reversible?)

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
    (cljs.core/apply fun (cons arg args))))

(defn ^:export juxt [& fns]
  (fn [& args]
    (into-array (map #(cljs.core/apply % args) fns))))

(defn ^:export knit [& fns]
  (fn [args]
    (into-array (map #(% %2) fns args))))

;; Useful fns

(def ^:export sum (fn [s n] (+ s n)))
(def ^:export inc (fn [n] (+ n 1)))
(def ^:export dec (fn [n] (- n 1)))
(def ^:export is-even (fn [n] (zero? (mod n 2))))
(def ^:export is-odd (fn [n] (== (mod n 2) 1)))

(defn ^:export each [xs f]
  (doseq [x xs]
    (f x)))

(def ^:export identity cljs.core/identity)
(def ^:export constantly cljs.core/constantly)

(def ^:export clj-to-js cljs.core/clj->js)
(defn ^:export js-to-clj 
  ([x] (cljs.core/js->clj x))
  ([x keywordize-keys] (cljs.core/js->clj x :keywordize-keys keywordize-keys)))

(defn ^:export proxy [coll]
  (if (exists? js/Proxy)
    (let [handler (js-obj
                    "has" (fn [k] (contains? coll k))
                    "hasOwn" (fn [k] (contains? coll k))
                    "get" (fn [target k]
                            (let [v (get coll k ::not-found)]
                              (cond
                                (keyword-identical? v ::not-found)
                                (when (and (counted? coll)
                                           (identical? k "length"))
                                  (count coll))
                                :else v)))
                    "set" (fn [target k v])
                    "enumerate" (fn [] (into-array (keys coll)))
                    "keys" (fn []
                             (cond
                               (map? coll)
                               (into-array (keys coll))

                               (vector? coll)
                               (into-array (range (count coll)))

                               :else nil)))]
      (js/Proxy.create handler))
    (throw (js/Error. "ES6 Proxy not supported!"))))

;; =============================================================================
;; Node.js Inspection support

(make-inspectable
  cljs.core.LazySeq
  cljs.core.IndexedSeq
  cljs.core.RSeq
  cljs.core.PersistentTreeMapSeq
  cljs.core.NodeSeq
  cljs.core.ArrayNodeSeq
  cljs.core.List
  cljs.core.Cons
  cljs.core.EmptyList
  cljs.core.PersistentVector
  cljs.core.ChunkedCons
  cljs.core.ChunkedSeq
  cljs.core.Subvec
  cljs.core.BlackNode
  cljs.core.RedNode
  cljs.core.ObjMap
  cljs.core.PersistentArrayMap
  cljs.core.PersistentHashMap
  cljs.core.PersistentTreeMap
  cljs.core.PersistentHashSet
  cljs.core.PersistentTreeSet
  cljs.core.Range
  cljs.core.PersistentQueue)

