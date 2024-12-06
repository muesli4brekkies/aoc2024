(ns d5.1 (:require [clojure.string :as s]))

(defn pass-rule? [line rule] (= rule (filter (fn [c] (some #(= % c) rule)) line)))

(defn match-rules [rules line]
  (filter (fn [[x y]] (= 2 (reduce (fn [a b] (+ a (if (or (= b x) (= b y)) 1 0))) 0 line))) rules))

(defn check [rules line] (every? (partial pass-rule? line) (match-rules rules line)))

(defn split-input [in]
  (let [lines (s/split-lines in)
        split (first (filter #(empty? (nth lines %)) (-> lines count range)))
        [r c] (split-at split lines)]
    [(map #(s/split % #"\|") r) (map #(s/split % #",") c)]))

(defn solve [in]
  (->>
   in
   split-input
   ((fn [[rules puzzle]] (filter #(check rules %) (drop 1 puzzle))))
   (map #(read-string (nth % (-> % count (/ 2)))))
   (apply +)))