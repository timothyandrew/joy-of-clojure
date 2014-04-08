(ns joy.triangle)

(defn triangle [n]
  (if (zero? n)
    0
    (+ n (triangle (dec n)))))

(defn inf-triangles [n]
  {:head (triangle n)
   :tail (delay (inf-triangles (inc n)))})

(defn head [l]
  (:head l))

(defn tail [l]
  (force (:tail l)))

(defn taker [n l]
  (loop [t n, src l, ret []]
    (if (zero? t)
      ret
      (recur (dec t) (tail src) (conj ret (head src))))))

(defn nthr [l n]
  (if (zero? n)
    (head l)
    (recur (tail l) (dec n))))
