(ns d1p1.core
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
  (->>
   l
   (sort)
   (interleave (sort r))
   (partition 2)
   (reduce (fn [acc [l r]] (+ acc (abs (- l r)))) 0)))

(defn -main
  []
  (->
   "../input.txt"
   (slurp)
   (split-lr)
   (crunch)
   (println)))