(ns d1p2.core
  (:gen-class))

(require '[clojure.string :as s])

(defn unzip
  [[lacc racc] line]
  (let [[l r] (map read-string (s/split line #"\s+"))]
    [(conj lacc l) (conj racc r)]))

(defn split-lr
  [input]
  (reduce unzip [[] []] (s/split-lines input)))

(defn crunch
  [[l r]]
  (let [frq (frequencies r)]
    (reduce #(+ %1 (* %2 (or (get frq %2) 0))) 0 l)))

(defn -main
  []
  (->
   "../input.txt"
   (slurp)
   (split-lr)
   (crunch)
   (println)))