(ns mori.macros
  (:require [cljs.compiler :as comp]
            [cljs.analyzer :as ana]
            [cljs.analyzer.api :as ana-api]))

(alias 'core 'clojure.core)

(defn make-inspectable-1 [x]
  `(aset (.-prototype ~x) "inspect"
     (fn []
       (~'this-as coll#
         (.toString coll#)))))

(defmacro make-inspectable [& xs]
  `(do
     ~@(map make-inspectable-1 xs)))

(defmacro mori-export [exportf coref]
  (let [{:keys [ns name methods]} (ana-api/resolve &env coref)]
    `(do
       (def ~(vary-meta exportf assoc :export true) ~coref)
       ~@(when-not (= 1 (count methods))
           (map
             (fn [{:keys [variadic max-fixed-arity]}]
               (if variadic
                 `(js/goog.exportSymbol
                    ~(str ana/*cljs-ns* "." (core/name exportf) ".fn")
                    ~(symbol (str ns) (str (core/name name) ".cljs$core$IFn$_invoke$variadic")))
                 `(js/goog.exportSymbol
                    ~(str ana/*cljs-ns* "." (core/name exportf) ".f" max-fixed-arity)
                    ~(symbol (str ns) (str (core/name name) ".cljs$core$IFn$_invoke$arity$" max-fixed-arity)))))
             methods)))))

(comment

  ;; setup
  (require
    '[cljs.env :as env]
    '[clojure.pprint :as pp])

  (def cenv (env/default-compiler-env))
  (def aenv
    (update-in (ana/empty-env) [:ns]
      assoc
      :name 'mori
      :excludes '#{vector assoc-in conj}))

  ;; analyze core
  (binding [ana/*cljs-ns* 'cljs.user]
    (env/with-compiler-env cenv
      (comp/with-core-cljs nil
        (fn []))))

  ;; check fn
  (pp/pprint
    (env/with-compiler-env cenv
     (ana-api/resolve aenv 'cljs.core/assoc-in)))

  ;; verify macro
  (env/with-compiler-env cenv
    (binding [ana/*cljs-ns* 'mori]
      (pp/write
        (ana/macroexpand-1 aenv
          `(mori-export assocIn cljs.core/assoc-in))
        ;:dispatch pp/code-dispatch
        )))

  (env/with-compiler-env cenv
    (binding [ana/*cljs-ns* 'mori
              *ns* (create-ns 'mori)]
      (pp/write
        (ana/macroexpand-1 aenv
          `(mori-export conj cljs.core/conj))
        ;:dispatch pp/code-dispatch
        )))

  )
