(ns joy.queues
  (use [clojure.core]
       [clojure.repl]))

(def schedule
  (conj clojure.lang.PersistentQueue/EMPTY :wake-up :brush-teeth :go-to-work))
