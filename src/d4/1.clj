(ns d4.1 (:require [clojure.string :as s]))

(defn match [s] (let [x "XMAS"] (or (= s x) (= s (s/reverse x)))))

(defn count-xmas [string]
  (loop [s string n 0]
    (if (-> s count (< 4))
      n
      (recur (apply str (drop 1 s)) (+ n (if (->> s (take 4) (apply str) match) 1 0))))))

(defn zigzag [lines]
  (let [len (count (first lines))]
    (->>
     (concat
      (for [j (range len)] (for [i (range (- len j))] (get (get lines i) (+ i j))))
      (for [k (map #(+ 1 %) (range len))] (for [i (range (- len k))] (get (get lines (+ i k)) i))))
     (map #(apply str %)))))

(defn solve [in]
  (let [lines (s/split-lines in)
        rot (map #(apply str %) (apply map vector lines))
        zig (zigzag lines)
        giz (zigzag (vec (map #(->> % reverse (apply str)) lines)))]
    (reduce #(+ %1 (count-xmas %2)) 0 (concat lines rot zig giz))))
             

