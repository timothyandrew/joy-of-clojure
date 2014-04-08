(ns joy.closures)

(defn times-n [n]  
  (fn [y] (* y n)))

(def times-two
  (times-n 2))

(def add-and-get
  (let [ai (java.util.concurrent.atomic.AtomicInteger.)]
    (fn [y] (.addAndGet ai y))))
