(ns joy.recursion)

(defn pow [base exp]
  (letfn [(kapow [base exp acc]
            (if (zero? exp)
              acc
              (recur base (dec exp) (* base acc))))]
    (kapow base exp 1)))

(defn gcd [x y]
  (cond
   (> x y) (recur (- x y) y)
   (< x y) (recur x (- y x))
   :else x))
