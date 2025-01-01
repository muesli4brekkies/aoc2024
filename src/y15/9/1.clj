(ns y15.9.1
  (:require [clojure.math.combinatorics :as c]
            [clojure.string :as s]))

(defn solve [in]
  (let [cities (distinct (re-seq #"[A-Za-z]{3,}" in))
        dirs (reduce
              #(let [[from _ to _ dist] (s/split %2 #" ")
                     dist (Integer/parseInt dist)]
                 (assoc (assoc %1 to (assoc (%1 to) from dist)) from (assoc (%1 from) to dist)))
              (apply hash-map (interleave cities (repeat (count cities) {})))
              (s/split-lines in))]
    (reduce
     (fn [ac rte] (min ac (reduce (fn [ac [fr to]] (+ ac ((dirs fr) to))) 0 (partition 2 1 rte))))
     ##Inf
     (c/permutations cities))))