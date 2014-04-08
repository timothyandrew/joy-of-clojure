(ns joy.units)

(def simple-metric {:meter 1,
                    :km 1000,
                    :cm 1/100,
                    :mm [1/10 :cm]})

(defn convert [context descriptor]
  (reduce (fn [result [mag unit]]
            (+ result
              (let [val (unit context)]
                (if (vector? val)
                  (* mag (convert context val))
                  (* mag val)))))
          0
          (partition 2 descriptor)))
