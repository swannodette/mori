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
    list vector array-map hash-map zipmap set sorted-set keyword symbol
    sorted-set-by sorted-map sorted-map-by
    sum inc dec even? odd? subseq compare
    meta with-meta vary-meta
    apply])
  (:use-macros [mori.macros :only [make-inspectable]])
  (:require [clojure.set :as set]
            [clojure.data :as data]
            [cljs.reader :as reader]))

(def ^:export apply cljs.core/apply)
(def ^:export count cljs.core/count)
(def ^:export distinct cljs.core/distinct)
(def ^:export empty cljs.core/empty)
(def ^:export first cljs.core/first)
(def ^:export second cljs.core/second)
(def ^:export next cljs.core/next)
(def ^:export rest cljs.core/rest)
(def ^:export seq cljs.core/seq)
(def ^:export conj cljs.core/conj)
(def ^:export cons cljs.core/cons)
(def ^:export find cljs.core/find)
(def ^:export nth cljs.core/nth)
(def ^:export last cljs.core/last)
(def ^:export assoc cljs.core/assoc)
(def ^:export dissoc cljs.core/dissoc)
(def ^:export getIn cljs.core/get-in)
(def ^:export updateIn cljs.core/update-in)
(def ^:export assocIn cljs.core/assoc-in)
(def ^:export fnil cljs.core/fnil)
(def ^:export disj cljs.core/disj)
(def ^:export pop cljs.core/pop)
(def ^:export peek cljs.core/peek)
(def ^:export hash cljs.core/hash)
(def ^:export get cljs.core/get)
(def ^:export hasKey cljs.core/contains?)
(def ^:export isEmpty cljs.core/empty?)
(def ^:export reverse cljs.core/reverse)
(def ^:export take cljs.core/take)
(def ^:export drop cljs.core/drop)
(def ^:export takeNth cljs.core/take-nth)
(def ^:export partition cljs.core/partition)
(def ^:export partitionAll cljs.core/partition-all)
(def ^:export partitionBy cljs.core/partition-by)
(def ^:export iterate cljs.core/iterate)
(def ^:export into cljs.core/into)
(def ^:export merge cljs.core/merge)
(def ^:export mergeWith cljs.core/merge-with)
(def ^:export subvec cljs.core/subvec)
(def ^:export takeWhile cljs.core/take-while)
(def ^:export dropWhile cljs.core/drop-while)
(def ^:export groupBy cljs.core/group-by)
(def ^:export interpose cljs.core/interpose)
(def ^:export interleave cljs.core/interleave)
(def ^:export concat cljs.core/concat)

(defn ^:export conj1 [^not-native coll x]
  (cljs.core/-conj coll x))

(defn sequential-or-array? [x]
  (or (array? x)
      (sequential? x)))

(defn ^:export flatten [x]
  (cljs.core/filter #(not (sequential-or-array? %))
    (cljs.core/rest (tree-seq sequential-or-array? seq x))))

; The real lazy-seq is a macro, but it just expands its body into a function
(defn ^:export lazySeq [f]
  (new cljs.core/LazySeq nil f nil nil))

(def ^:export keys cljs.core/keys)
(def ^:export selectKeys cljs.core/select-keys)
(def ^:export vals cljs.core/vals)
(def ^:export primSeq cljs.core/prim-seq)
(def ^:export map cljs.core/map)
(def ^:export mapIndexed cljs.core/map-indexed)
(def ^:export mapcat cljs.core/mapcat)
(def ^:export reduce cljs.core/reduce)
(def ^:export reduceKV cljs.core/reduce-kv)
(def ^:export keep cljs.core/keep)
(def ^:export keepIndexed cljs.core/keep-indexed)
(def ^:export filter cljs.core/filter)
(def ^:export remove cljs.core/remove)
(def ^:export some cljs.core/some)
(def ^:export every cljs.core/every?)
(def ^:export equals cljs.core/=)
(def ^:export range cljs.core/range)
(def ^:export repeat cljs.core/repeat)
(def ^:export repeatedly cljs.core/repeatedly)
(def ^:export sort cljs.core/sort)
(def ^:export sortBy cljs.core/sort-by)
(def ^:export intoArray cljs.core/into-array)
(def ^:export subseq cljs.core/subseq)
(def ^:export dedupe cljs.core/dedupe)

;; transducers

(def ^:export transduce cljs.core/transduce)
(def ^:export eduction cljs.core/eduction)
(def ^:export sequence cljs.core/sequence)
(def ^:export completing cljs.core/completing)

;; constructors

(def ^:export list cljs.core/list)
(def ^:export vector cljs.core/vector)
(def ^:export arrayMap cljs.core/array-map)
(def ^:export hashMap cljs.core/hash-map)
(def ^:export set cljs.core/set)
(def ^:export sortedSet cljs.core/sorted-set)
(def ^:export sortedSetBy cljs.core/sorted-set-by)
(def ^:export sortedMap cljs.core/sorted-map)
(def ^:export sortedMapBy cljs.core/sorted-map-by)
(def ^:export queue (fn [& args] (into cljs.core.PersistentQueue.EMPTY args)))

(def ^:export keyword cljs.core/keyword)
(def ^:export symbol cljs.core/symbol)

(def ^:export zipmap cljs.core/zipmap)

;; Predicates
(def ^:export isList cljs.core/list?)
(def ^:export isSeq cljs.core/seq?)
(def ^:export isVector cljs.core/vector?)
(def ^:export isMap cljs.core/map?)
(def ^:export isSet cljs.core/set?)

(def ^:export isKeyword cljs.core/keyword?)
(def ^:export isSymbol cljs.core/symbol?)

(def ^:export isCollection cljs.core/coll?)
(def ^:export isSequential cljs.core/sequential?)
(def ^:export isAssociative cljs.core/associative?)
(def ^:export isCounted cljs.core/counted?)
(def ^:export isIndexed cljs.core/indexed?)
(def ^:export isReduceable cljs.core/reduceable?)
(def ^:export isSeqable cljs.core/seqable?)
(def ^:export isReversible cljs.core/reversible?)

;; Set ops
(def ^:export union set/union)
(def ^:export intersection set/intersection)
(def ^:export difference set/difference)
(def ^:export join set/join)
(def ^:export index set/index)
(def ^:export project set/project)
(def ^:export mapInvert set/map-invert)
(def ^:export rename set/rename)
(def ^:export renameKeys set/rename-keys)
(def ^:export isSubset set/subset?)
(def ^:export isSuperset set/superset?)

;; Comparisons

(def ^:export notEquals cljs.core/not=)
(def ^:export gt cljs.core/>)
(def ^:export gte cljs.core/>=)
(def ^:export lt cljs.core/<)
(def ^:export lte cljs.core/<=)
(def ^:export compare cljs.core/compare)

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
    (intoArray (map #(cljs.core/apply % args) fns))))

(defn ^:export knit [& fns]
  (fn [args]
    (intoArray (map #(% %2) fns args))))

;; Data fns

(def ^:export diff data/diff)

;; Useful fns

(def ^:export sum (fn [s n] (+ s n)))
(def ^:export inc (fn [n] (+ n 1)))
(def ^:export dec (fn [n] (- n 1)))
(def ^:export isEven (fn [n] (zero? (mod n 2))))
(def ^:export isOdd (fn [n] (== (mod n 2) 1)))

(defn ^:export each [xs f]
  (doseq [x xs]
    (f x)))

(def ^:export identity cljs.core/identity)
(def ^:export constantly cljs.core/constantly)

(def ^:export toJs cljs.core/clj->js)
(defn ^:export toClj
  ([x] (cljs.core/js->clj x))
  ([x keywordize-keys] (cljs.core/js->clj x :keywordize-keys keywordize-keys)))

(defn ^:export parse [s]
  (reader/read-string s))

(defn ^:export configure [variable value]
  (case variable
    "print-length" (set! *print-length* value)
    "print-level" (set! *print-level* value)))

(def ^:export meta cljs.core/meta)
(def ^:export withMeta cljs.core/with-meta)
(def ^:export varyMeta cljs.core/vary-meta)
(def ^:export alterMeta cljs.core/alter-meta!)
(def ^:export resetMeta cljs.core/reset-meta!)

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
