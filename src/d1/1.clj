(ns d1.1 (:require [clojure.string :as s]))

(defn ins-ord [l x] (let [[bef aft] (split-with #(> x %) l)] (-> bef vec (conj x) (into aft))))

(defn solve [in]
  (->>
   in
   (#(s/split % #"\s+|\n"))
   (map read-string)
   (map-indexed vector)
   (reduce (fn [[l r] [i b]] (if (zero? (mod i 2)) [(ins-ord l b) r] [l (ins-ord r b)])) [[] []])
   (apply map vector)
   (reduce (fn [a [x y]] (+ a (abs (- x y)))) 0)))
