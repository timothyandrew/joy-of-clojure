(ns joy.macros)

(defmacro do-until [& clauses]
  (when clauses
    (list 'clojure.core/when
          (first clauses)
          (if (next clauses)
            (second clauses)
            (throw (IllegalArgumentException. "Even number of forms required")))
          (cons 'do-until (nnext clauses)))))

(defmacro unless [condition & body]
  '(if (not ~condition)
     (do ~@body)))

(defmacro def-watched [name & value]
  `(do
     (def ~name ~@value)
     (add-watch (var ~name)
                :re-bind
                (fn [~'key ~'r old# new#]
                  (println old# " -> " new#)))))

