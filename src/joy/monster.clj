(ns joy.monster)

(defmacro domain [name & body]
  `{:tag :domain
    :attrs {:name (str '~name)}
    :content [~@body]})

(declare handle-things)
(defmacro grouping [name & body]
  `{:tag :grouping
    :attrs {:name (str '~name)}
    :content [~@(handle-things body)]})

(declare grok-attrs grok-props)
(defn handle-things [things]
  (for [t things]
    {:tag :thing
     :attrs (grok-attrs (take-while (comp not vector?) t))
     :content  [(grok-props (drop-while (comp not vector?) t))]}))

(defn grok-attrs [[name & attrs]]
  (into {:name (str name)}
        (for [a attrs]
          (cond
           (list? a) {:isa (str (second a))}
           (string? a) {:comment a}))))

(defn grok-props [props]
  {:tag :properties
   :attrs nil
   :content (apply vector (for [[name & _] props]
                            {:tag :property
                             :attrs {:name (str name) }
                             :content nil}))})

(def d
  (domain man-vs-monster
          (grouping people
                    (Human "A Stock Human")
                    (Man (isa Human)
                         "A man, baby"
                         [name]
                         [has-beard?]))
          (grouping monsters
                    (Chupacabra
                     "A fierce, yet elusive creature"
                     [eats-goats?]))))
