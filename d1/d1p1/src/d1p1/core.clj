(ns d1p1.core
  (:gen-class))

(require '[clojure.string :as s])

(defn unzip
  [[lacc racc] line]
  (let [[l r] (s/split line #"\s+")]
    [(conj lacc l) (conj racc r)]))

(defn split-lr
  [input]
  (reduce unzip [[] []] (s/split-lines input)))

(defn crunch
  [[l r]]
  (->>
   l
   (sort)
   (interleave (sort r))
   (map read-string)
   (partition 2)
   (reduce #(+ %1 (abs (- (first %2) (last %2)))) 0)))

(defn -main
  []
  (->
   "../input.txt"
   (slurp)
   (split-lr)
   (crunch)
   (println)))