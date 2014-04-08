(ns joy.chess)

(defn build-move [& {:keys [from to castle? promotion]}]
  {:pre [from to]}
  (Move. from to castle? promotion))

(defrecord Move [from to castle? promotion]
  Object
  (toString [this]
    (str "Move " (:from this) " to " (:to this)
         (if (:castle? this)
           " castle"
           (if-let [p (:promotion this)]
             (str " promote to " p)
             "")))))
