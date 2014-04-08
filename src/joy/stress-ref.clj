(ns joy.stress-ref)

(defn stress-ref [r]
  (let [slow-tries (atom 0)]
    (future
      (dosync
       (swap! slow-tries inc)
       (Thread/sleep 200)
       @r)
      (spit "/tmp/foo" (format "r is %s, history: %d, after: %d tries\n" @r (.getHistoryCount r) @slow-tries)
            :append true))

    (dotimes [i 500]
      (Thread/sleep 10)
      (dosync
       (alter r inc)))
    :done))
