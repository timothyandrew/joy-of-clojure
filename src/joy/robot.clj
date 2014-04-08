(ns joy.robot)

(def bearings [{:x  0, :y  1}
               {:x  1, :y  0}
               {:x  0, :y -1}
               {:x -1, :y  0}])

(defn bot [x y bearing-num]
  {:coords [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn []
              (bot (+ x (:x (bearings bearing-num)))
                   (+ y (:y (bearings bearing-num)))
                   bearing-num))
   :turn-left  (fn [] (bot x y (mod (- 1 bearing-num) 4)))
   :turn-right (fn [] (bot x y (mod (+ 1 bearing-num) 4)))})

(defn mirror-bot [x y bearing-num]
  {:coords [x y]
   :bearing ([:north :east :south :west] bearing-num)
   :forward (fn []
              (mirror-bot (- x (:x (bearings bearing-num)))
                          (- y (:y (bearings bearing-num)))
                          bearing-num))
   :turn-left  (fn [] (mirror-bot x y (mod (- 1 bearing-num) 4)))
   :turn-right (fn [] (mirror-bot x y (mod (- 1 bearing-num) 4)))})
