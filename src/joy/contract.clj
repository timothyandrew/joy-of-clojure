(ns joy.contract)

(declare collect-bodies)
(defmacro contract [name & forms]
  (list* `fn name (collect-bodies forms)))

(declare build-contract)
(defn collect-bodies [forms]
  (for [form (partition 3 forms)]
    (build-contract form)))

(defn build-contract [c]
  (let [args (first c)]
    (list
     (into '[f] args)
     (apply merge (for [con (rest c)]
                    (cond
                     (= (first con) 'require) {:pre (vec (rest con))}
                     (= (first con) 'ensure)  {:post (vec (rest con))}
                     :else (throw (Exception. (str "Unknown tag" (first con)))))))
     (list* 'f args))))

(def doubler-contract
  (contract doubler
            [x]
            (require
             (pos? x))
            (ensure
             (= (* 2 x) %))))
