(ns d2.1 (:require [clojure.string :as s]))

(defn diffs
  [line res]
  (if (= 1 (count line))
    res
    (recur (drop 1 line) (conj res (- (first line) (second line))))))

(defn isvalid?
  [line]
  (and (every? #((if (< 0 (first line)) < >) 0 %) line)
       (every? #(and (>= 3 (abs %)) (not (= % 0))) line)))

(defn solve
  [in]
  (->>
   in
   s/split-lines
   (map #(s/split % #"\s+"))
   (map #(map read-string %))
   (map #(diffs % []))
   (filter isvalid?)
   count))