(ns y24.4.1 (:require [clojure.math.combinatorics :as c]))

(defn search [wid in i mul add]
  (if (every? (fn [[n c]] (= c (get in (+ i (* n add) (* n mul wid))))) [[1 \M] [2 \A] [3 \S]])
    1 0))

(defn solve [in]
  (let [[len wid] [(count in) (inc (count (take-while #(not (= % \newline)) in)))]]
    (reduce
     (fn [acc i]
       (+ acc
          (if (= \X (get in i))
            (reduce
             (fn [acc [mul add]] (+ acc (search wid in i mul add))) 0
             (c/selections [-1 0 1] 2))
            0))) 0 (range len))))