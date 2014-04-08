(ns joy.atoms
  (require [joy.mutation :refer [dothreads!]]))

(def *time* (atom 0))

(defn tick []
  (swap! *time* inc))

(defn manipulable-memoize [function]
  (let [cache (atom {})]
    (with-meta
      (fn [& args]
        (or (second (find @cache args))
            (let [ret (apply function args)]
              (swap! cache assoc args ret)
              ret)))
      {:cache cache})))

(def slowly (fn [x] (Thread/sleep 1000) x))

(def sometimes-slowly (manipulable-memoize slowly))



