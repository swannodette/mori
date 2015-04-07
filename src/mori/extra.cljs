(ns mori.extra
  (:refer-clojure :exclude
   [distinct first second next rest seq cons last
    get-in update-in assoc-in fnil
    take drop take-nth partition partition-all partition-by iterate
    merge-with
    take-while drop-while group-by
    interpose interleave concat flatten
    prim-seq lazy-seq keep keep-indexed
    map mapcat map-indexed reduce reduce-kv filter remove some every?
    transduce eduction sequence dedupe completing
    repeat repeatedly
    partial comp juxt
    identity constantly
    zipmap sorted-set keyword symbol
    sorted-set-by sorted-map sorted-map-by
    sum inc dec even? odd? subseq
    meta with-meta vary-meta
    apply hash select-keys into-array list range])
  (:require-macros
   [mori.macros :refer [mori-export make-inspectable]])
  (:require [clojure.set :as set]
            [cljs.reader :as reader]
            [clojure.data :as data]
            [clojure.zip :as zip]))

(mori-export range cljs.core/range)
(mori-export list cljs.core/list)
(mori-export intoArray cljs.core/into-array)
(mori-export selectKeys cljs.core/select-keys)
(mori-export hash cljs.core/hash)
(mori-export apply cljs.core/apply)
(mori-export distinct cljs.core/distinct)
(mori-export first cljs.core/first)
(mori-export second cljs.core/second)
(mori-export next cljs.core/next)
(mori-export rest cljs.core/rest)
(mori-export seq cljs.core/seq)
(mori-export cons cljs.core/cons)
(mori-export last cljs.core/last)
(mori-export getIn cljs.core/get-in)
(mori-export updateIn cljs.core/update-in)
(mori-export assocIn cljs.core/assoc-in)
(mori-export fnil cljs.core/fnil)
(mori-export hasKey cljs.core/contains?)
(mori-export take cljs.core/take)
(mori-export drop cljs.core/drop)
(mori-export takeNth cljs.core/take-nth)
(mori-export partition cljs.core/partition)
(mori-export partitionAll cljs.core/partition-all)
(mori-export partitionBy cljs.core/partition-by)
(mori-export iterate cljs.core/iterate)
(mori-export mergeWith cljs.core/merge-with)
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
    (cljs.core/rest (tree-seq sequential-or-array? cljs.core/seq x))))

; The real lazy-seq is a macro, but it just expands its body into a function
(defn ^:export lazySeq [f]
  (new cljs.core/LazySeq nil f nil nil))

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
(mori-export repeat cljs.core/repeat)
(mori-export repeatedly cljs.core/repeatedly)
(mori-export subseq cljs.core/subseq)
(mori-export dedupe cljs.core/dedupe)

;; transducers

(mori-export transduce cljs.core/transduce)
(mori-export eduction cljs.core/eduction)
(mori-export sequence cljs.core/sequence)
(mori-export completing cljs.core/completing)

;; constructors

(mori-export sortedSet cljs.core/sorted-set)
(mori-export SortedSet cljs.core/PersistentTreeSet)
(mori-export sortedSetBy cljs.core/sorted-set-by)
(mori-export sortedMap cljs.core/sorted-map)
(mori-export SortedMap cljs.core/PersistentTreeMap)
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

;; HOFs

(mori-export partial cljs.core/partial)
(mori-export comp cljs.core/comp)

(defn ^:export pipeline [& args]
  (cljs.core/reduce #(%2 %1) args))

(defn ^:export curry [fun & args]
  (fn [arg]
    (cljs.core/apply fun (cljs.core/cons arg args))))

(defn ^:export juxt [& fns]
  (fn [& args]
    (cljs.core/into-array (cljs.core/map #(cljs.core/apply % args) fns))))

(defn ^:export knit [& fns]
  (fn [args]
    (cljs.core/into-array (cljs.core/map #(% %2) fns args))))

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

(mori-export parse reader/read-string)
(mori-export diff data/diff)

(mori-export zipper zip/zipper)		
(mori-export seqZip zip/seq-zip)		
(mori-export vectorZip zip/vector-zip)		
(mori-export node zip/node)		
(mori-export isBranch zip/branch?)		
(mori-export children zip/children)		
(mori-export makeNode zip/make-node)		
(mori-export path zip/path)		
(mori-export lefts zip/lefts)		
(mori-export rights zip/rights)		
(mori-export down zip/down)		
(mori-export up zip/up)		
(mori-export root zip/root)		
(mori-export right zip/right)		
(mori-export rightmost zip/rightmost)		
(mori-export left zip/left)		
(mori-export leftmost zip/leftmost)		
(mori-export insertLeft zip/insert-left)		
(mori-export insertRight zip/insert-right)		
(mori-export replace zip/replace)		
(mori-export edit zip/edit)		
(mori-export insertChild zip/insert-child)		
(mori-export appendChild zip/append-child)		
(mori-export next zip/next)		
(mori-export prev zip/prev)		
(mori-export isEnd zip/end?)		
(mori-export remove zip/remove)

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
