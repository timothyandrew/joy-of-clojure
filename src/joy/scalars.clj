(ns joy.scalars
  (:require clojure.core))

(defn pour [lb ub]
  (cond (= ub :tojours)
        (iterate inc lb)
        :else (range lb ub)))

(defn best [f seq]
  (reduce #(if (f %1 %2) %1 %2) seq))
