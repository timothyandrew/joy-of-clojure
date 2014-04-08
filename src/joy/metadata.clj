(ns joy.metadata)

(defn join
  { :test (fn []
            (assert
              (= (join "," [1 2 3]) "1,3,3"))) }
  [sep s]
  (apply str (interpose sep s)))
