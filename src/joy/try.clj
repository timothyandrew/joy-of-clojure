(ns joyx.foobar)

(defn print-down-from [x]
  (when (pos? x)
    (println x)
    (recur (dec x))))

(defn sum-down-from [initial-x]
  (loop [sum 0 x initial-x]
    (if (pos? x)
      (recur (+ sum x) (dec x))
      sum)))

(defn print-seq [s]
  (when (seq s)
    (prn (first s))
    (recur (rest s))))

(def guys-whole-name ["Guy" "Lewis" "Steele"])

(defn combine-name [[first middle last]]
  (str last ", " first " " middle))

(def guys-name-map
  {:first "Guy" :middle "Lewis" :last "Steele"})

(def guys-name-list
  [:first "Guy" :middle "Lewis" :last "Steele"])

(defn combine-name-map [& args]
  (let [{:keys [title first middle last]
         :as whole-name
         :or {title "Mr."}} args]
    (println whole-name)
    (str title " " last ", " first " " middle)))
