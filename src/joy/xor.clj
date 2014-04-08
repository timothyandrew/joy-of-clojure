(ns joy.xor
  (:use (clojure.core)))

(defn f-values [f max-x max-y]
  (for [x (range max-x) y (range max-y)]
    [x y (rem (f x y) 256)]))

(defn clear [gfx] (.clearRect gfx 0 0 200 200))

(defn draw [f x y gfx]
  (clear gfx)
  (.setSize gfx x y)
  (doseq [[x y v] (f-values f x y)]
    (.setColor gfx (java.awt.Color. v v v))
    (.fillRect gfx x y 1 1)))
