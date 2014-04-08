(ns joy.udp
  (:refer-clojure :exclude [get]))

(defn beget [this proto]
  (assoc this ::prototype proto))

(defn get [m k]
  (when m
    (if-let [[_ v] (find m k)]
      v
      (recur (::prototype m) k))))

(def put assoc)

(def cat {:likes-dogs true :ocd-bathing true})
(def morris (beget {:likes-9lives true} cat))
(def post-traumatic-morris (beget {:likes-dogs nil} morris))

(def clone (partial beget {}))
(def unix   {:os ::unix, :c-compiler "cc", :home "/home", :dev "/dev"})
(def osx  (-> (clone unix)
              (put :os ::osx)
              (put :llvm-compiler "clang")
              (put :home "/Users")))

(defmulti compiler :os)
(defmethod compiler ::unix [m] (get m :c-compiler))
(defmethod compiler ::osx [m] (get m :llvm-compiler))

(defmulti home :os)
(defmethod home ::unix [m] (get m :home))
(defmethod home ::bsd [m] "/home")

(defmulti compile-cmd (juxt :os compiler))
(defmethod compile-cmd [::osx "clang"] [m] (str "/usr/bin/" (compiler m)))
(defmethod compile-cmd :default [m] (str "Unsure where to locate " (compiler m)))

