(ns joy.name-capture)

(defmacro resolution [] `x)

(defmacro awhen [expr & body]
  `(let [~'it ~expr]
     (when ~'it
       ~@body)))
