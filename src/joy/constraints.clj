(ns joy.constraints)

(defn vegan-constraints [f m]
  {:pre [(:veggie m)]
   :post [(:veggie %) (nil? (:meat %))]}
  (f m))

(defn put-things [m]
  (into m {:meat "beef" :veggie "broccoli"}))
