(ns joy.pathfinding
  (use joy.neighbors))

(def world [[  1   1   1   1    1]
            [999 999 999 999    1]
            [  1   1   1   1    1]
            [  1 999 999 999  999]
            [  1   1   1   1    1]])

(defn estimate-cost [step-cost-est size y x]
  (* step-cost-est
     (- (* size 2) y x 2)))

(defn path-cost [node-cost cheapest-nbr]
  (+ node-cost
     (or (:cost cheapest-nbr) 0)))

(defn total-cost [newcost step-cost-est size y x]
  (+ newcost
     (estimate-cost step-cost-est size y x)))

(defn min-by [f coll]
  (when (seq coll)
    (reduce (fn [min other]
              (if (> (f min) (f other))
                other
                min))
            coll)))

(defn astar [start-yx step-est world]
  (let [size (count world)]
   (loop [steps 0
          work-todo (sorted-set [0 start-yx])
          routes (vec (repeat size (vec (repeat size nil))))]
     (if (empty? work-todo)
       [:steps steps :path (peek (peek routes))]     
       (let [[_ yx :as work-item] (first work-todo)
             rest-work-todo (disj work-todo work-item)
             nbr-yxs (neighbors size yx)
             cheapest-nbr (min-by :cost (keep #(get-in routes %) nbr-yxs))
             newcost (path-cost (get-in world yx) cheapest-nbr)
             oldcost (:cost (get-in routes yx))]
         (if (and oldcost (>= newcost oldcost))
           (recur (inc steps) rest-work-todo routes)
           (recur (inc steps)
                  (into rest-work-todo
                        (map
                         (fn [[y x]]
                           [(total-cost newcost step-est size y x) [y x]])
                         nbr-yxs))
                  (assoc-in routes
                            yx
                            {:cost newcost
                             :path (conj (:path cheapest-nbr []) yx)}))))))))
