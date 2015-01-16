(ns mori
  (:refer-clojure :exclude
   [count distinct empty first second next rest seq conj cons find nth last assoc dissoc
    get-in update-in assoc-in fnil disj pop peek hash get empty? reverse
    take drop take-nth partition partition-all partition-by iterate
    into merge merge-with subvec
    take-while drop-while group-by
    interpose interleave concat flatten
    keys select-keys vals
    prim-seq lazy-seq keep keep-indexed
    map mapcat map-indexed reduce reduce-kv filter remove some every? equiv
    transduce eduction sequence dedupe completing
    range repeat repeatedly sort sort-by
    into-array
    partial comp juxt
    identity constantly
    list vector vec array-map hash-map zipmap set sorted-set keyword symbol
    sorted-set-by sorted-map sorted-map-by
    sum inc dec even? odd? subseq compare
    meta with-meta vary-meta
    apply])
  (:use-macros [mori.macros :only [make-inspectable mori-export]])
  (:require [clojure.set :as set]))

(mori-export apply cljs.core/apply)
(mori-export count cljs.core/count)
(mori-export distinct cljs.core/distinct)
(mori-export empty cljs.core/empty)
(mori-export first cljs.core/first)
(mori-export second cljs.core/second)
(mori-export next cljs.core/next)
(mori-export rest cljs.core/rest)
(mori-export seq cljs.core/seq)
(mori-export conj cljs.core/conj)
(mori-export cons cljs.core/cons)
(mori-export find cljs.core/find)
(mori-export nth cljs.core/nth)
(mori-export last cljs.core/last)
(mori-export assoc cljs.core/assoc)
(mori-export dissoc cljs.core/dissoc)
(mori-export getIn cljs.core/get-in)
(mori-export updateIn cljs.core/update-in)
(mori-export assocIn cljs.core/assoc-in)
(mori-export fnil cljs.core/fnil)
(mori-export disj cljs.core/disj)
(mori-export pop cljs.core/pop)
(mori-export peek cljs.core/peek)
(mori-export hash cljs.core/hash)
(mori-export get cljs.core/get)
(mori-export hasKey cljs.core/contains?)
(mori-export isEmpty cljs.core/empty?)
(mori-export reverse cljs.core/reverse)
(mori-export take cljs.core/take)
(mori-export drop cljs.core/drop)
(mori-export takeNth cljs.core/take-nth)
(mori-export partition cljs.core/partition)
(mori-export partitionAll cljs.core/partition-all)
(mori-export partitionBy cljs.core/partition-by)
(mori-export iterate cljs.core/iterate)
(mori-export into cljs.core/into)
(mori-export merge cljs.core/merge)
(mori-export mergeWith cljs.core/merge-with)
(mori-export subvec cljs.core/subvec)
(mori-export takeWhile cljs.core/take-while)
(mori-export dropWhile cljs.core/drop-while)
(mori-export groupBy cljs.core/group-by)
(mori-export interpose cljs.core/interpose)
(mori-export interleave cljs.core/interleave)
(mori-export concat cljs.core/concat)

(defn sequential-or-array? [x]
  (or (array? x)
      (sequential? x)))

(defn ^:export flatten [x]
  (cljs.core/filter #(not (sequential-or-array? %))
    (cljs.core/rest (tree-seq sequential-or-array? seq x))))

; The real lazy-seq is a macro, but it just expands its body into a function
(defn ^:export lazySeq [f]
  (new cljs.core/LazySeq nil f nil nil))

(mori-export keys cljs.core/keys)
(mori-export selectKeys cljs.core/select-keys)
(mori-export vals cljs.core/vals)
(mori-export primSeq cljs.core/prim-seq)
(mori-export map cljs.core/map)
(mori-export mapIndexed cljs.core/map-indexed)
(mori-export mapcat cljs.core/mapcat)
(mori-export reduce cljs.core/reduce)
(mori-export reduceKV cljs.core/reduce-kv)
(mori-export keep cljs.core/keep)
(mori-export keepIndexed cljs.core/keep-indexed)
(mori-export filter cljs.core/filter)
(mori-export remove cljs.core/remove)
(mori-export some cljs.core/some)
(mori-export every cljs.core/every?)
(mori-export equals cljs.core/=)
(mori-export range cljs.core/range)
(mori-export repeat cljs.core/repeat)
(mori-export repeatedly cljs.core/repeatedly)
(mori-export sort cljs.core/sort)
(mori-export sortBy cljs.core/sort-by)
(mori-export intoArray cljs.core/into-array)
(mori-export subseq cljs.core/subseq)
(mori-export dedupe cljs.core/dedupe)

;; transducers

(mori-export transduce cljs.core/transduce)
(mori-export eduction cljs.core/eduction)
(mori-export sequence cljs.core/sequence)
(mori-export completing cljs.core/completing)

;; constructors

(mori-export list cljs.core/list)
(mori-export vector cljs.core/vector)
(mori-export vec cljs.core/vec)
(mori-export hashMap cljs.core/array-map)

(mori-export set cljs.core/set)
(mori-export sortedSet cljs.core/sorted-set)
(mori-export sortedSetBy cljs.core/sorted-set-by)
(mori-export sortedMap cljs.core/sorted-map)
(mori-export sortedMapBy cljs.core/sorted-map-by)

(def ^:export queue (fn [& args] (into cljs.core.PersistentQueue.EMPTY args)))

(mori-export keyword cljs.core/keyword)
(mori-export symbol cljs.core/symbol)

(mori-export zipmap cljs.core/zipmap)

;; Predicates
(mori-export isList cljs.core/list?)
(mori-export isSeq cljs.core/seq?)
(mori-export isVector cljs.core/vector?)
(mori-export isMap cljs.core/map?)
(mori-export isSet cljs.core/set?)

(mori-export isKeyword cljs.core/keyword?)
(mori-export isSymbol cljs.core/symbol?)

(mori-export isCollection cljs.core/coll?)
(mori-export isSequential cljs.core/sequential?)
(mori-export isAssociative cljs.core/associative?)
(mori-export isCounted cljs.core/counted?)
(mori-export isIndexed cljs.core/indexed?)
(mori-export isReduceable cljs.core/reduceable?)
(mori-export isSeqable cljs.core/seqable?)
(mori-export isReversible cljs.core/reversible?)

;; Set ops
(mori-export union set/union)
(mori-export intersection set/intersection)
(mori-export difference set/difference)
(mori-export join set/join)
(mori-export index set/index)
(mori-export project set/project)
(mori-export mapInvert set/map-invert)
(mori-export rename set/rename)
(mori-export renameKeys set/rename-keys)
(mori-export isSubset set/subset?)
(mori-export isSuperset set/superset?)

;; Comparisons

(mori-export notEquals cljs.core/not=)
(mori-export gt cljs.core/>)
(mori-export gte cljs.core/>=)
(mori-export lt cljs.core/<)
(mori-export lte cljs.core/<=)
(mori-export compare cljs.core/compare)

;; HOFs

(mori-export partial cljs.core/partial)
(mori-export comp cljs.core/comp)

(defn ^:export pipeline [& args]
  (reduce #(%2 %1) args))

(defn ^:export curry [fun & args]
  (fn [arg]
    (cljs.core/apply fun (cons arg args))))

(defn ^:export juxt [& fns]
  (fn [& args]
    (intoArray (map #(cljs.core/apply % args) fns))))

(defn ^:export knit [& fns]
  (fn [args]
    (intoArray (map #(% %2) fns args))))

;; Useful fns

(mori-export sum cljs.core/+)
(mori-export inc cljs.core/inc)
(mori-export dec cljs.core/dec)
(mori-export isEven cljs.core/even?)
(mori-export isOdd cljs.core/odd?)

(defn ^:export each [xs f]
  (doseq [x xs]
    (f x)))

(mori-export identity cljs.core/identity)
(mori-export constantly cljs.core/constantly)

(mori-export toJs cljs.core/clj->js)
(defn ^:export toClj
  ([x] (cljs.core/js->clj x))
  ([x keywordize-keys] (cljs.core/js->clj x :keywordize-keys keywordize-keys)))

(defn ^:export configure [variable value]
  (case variable
    "print-length" (set! *print-length* value)
    "print-level" (set! *print-level* value)))

(mori-export meta cljs.core/meta)
(mori-export withMeta cljs.core/with-meta)
(mori-export varyMeta cljs.core/vary-meta)
(mori-export alterMeta cljs.core/alter-meta!)
(mori-export resetMeta cljs.core/reset-meta!)

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
  cljs.core.Keyword
  cljs.core.Symbol
  cljs.core.PersistentQueue
  cljs.core.PersistentQueueSeq)
