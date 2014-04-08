(ns joy.records)

(defrecord TreeNode [val l r])

(defn xconj [t v]
  (cond
   (nil? t)       (TreeNode. v nil nil)
   (< v (:val t)) (TreeNode. (:val t) (xconj (:l t) v) (:r t))
   :else          (TreeNode. (:val t) (:l t) (xconj (:r t) v))))

(defn xseq [t]
  (when t
    (concat (xseq (:l t)) [(:val t)] (xseq (:r t)))))

(defprotocol FIXO
  (fixo-push [fixo value])
  (fixo-pop [fixo])
  (fixo-peek [fixo]))

(extend-type TreeNode
  FIXO
  (fixo-push [node value]
    (xconj node value)))

(extend-type clojure.lang.IPersistentVector
  FIXO
  (fixo-push [vector value]
    (conj vector value)))
