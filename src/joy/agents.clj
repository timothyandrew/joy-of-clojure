(ns joy.agents
  (require [joy.mutation :refer [dothreads!]]))

(def joy (agent []))

(defn slow-conj [coll item]
  (Thread/sleep 1000)
  (conj coll item))

(defn handle-log-error [agent error]
  (println "An action sent to the log-agent threw " error))

(def log-agent (agent 0))
(set-error-handler! log-agent handle-log-error)
(set-error-mode! log-agent :continue)

(defn do-log [msg-id message]
  (spit "/tmp/foo" (str msg-id ":" message "\n"))
  (inc msg-id))

(defn do-step [channel message]
  (Thread/sleep 30)
  (send-off log-agent do-log (str channel message)))

(defn three-step [channel]
  (do-step channel " ready to being (step 0)")
  (do-step channel " warming up (step 1)")
  (do-step channel " really getting going now (step 2)")
  (do-step channel " done! (step 3)"))

(defn all-together-now []
  (dothreads! #(three-step "alpha"))
  (dothreads! #(three-step "beta"))
  (dothreads! #(three-step "gamma")))

(defn exercise-agents [send-fn]
  (let [agents (map #(agent %) (range 50))]
    (doseq [a agents]
      (send-fn a (fn [_] (Thread/sleep 1000))))
    (doseq [a agents]
      (await a))))
