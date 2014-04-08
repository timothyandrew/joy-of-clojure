(ns joy.neighbors
  (use [clojure.core]
       [clojure.repl]))

(defn neighbors
  ([size yx]
     (neighbors [[-1 0] [1 0] [0 1] [0 -1]]
                size
                yx))
  
  ([deltas size yx]
     (filter
       (fn [new-yx]
         (every? #(< -1 % size) new-yx))
       (map
         #(vec (map + yx %))
         deltas))))
