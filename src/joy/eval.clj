(ns joy.eval)

(defn contextual-eval [ctx expr]
  (eval
   `(let [~@(mapcat (fn [[k v]] [k `~v]) ctx)]
      ~expr)))
