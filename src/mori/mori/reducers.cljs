(ns mori.reducers
  (:refer-clojure :exclude 
    [map filter remove take take-while drop flatten])
  (:require [clojure.core.reducers :as reducers]))

;; Reducers
(def ^:export map reducers/map)
(def ^:export filter reducers/filter)
(def ^:export remove reducers/remove)
(def ^:export take reducers/take)
(def ^:export take-while reducers/take-while)
(def ^:export drop reducers/drop)
(def ^:export flatten reducers/flatten)
