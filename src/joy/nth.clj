(ns joy.comp)

(defn fnth [n]
  (apply comp
         (cons
           first
           (take (dec n) (repeat rest)))))
