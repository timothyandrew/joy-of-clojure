(ns joy.lazy)

(defn rec-step [[x & xs]]
  (if x
    [x (rec-step xs)]
    []))

(defn lz-rec-step [s]
  (lazy-seq
   (if (seq s)
     [(first s) (lz-rec-step (rest s))]
     [])))
