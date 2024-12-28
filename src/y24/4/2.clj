(ns y24.4.2 (:require [clojure.string :as s]))

(defn check-chars [chars] (or (= chars [\S \M]) (= chars [\M \S])))

(defn check-mas [i j lines]
  (and
   (= (-> lines (get i) (get j)) \A)
   (let [[n w s e] [(dec i) (dec j) (inc i) (inc j)]
         nw (-> lines (get n) (get w))
         se (-> lines (get s) (get e))
         ne (-> lines (get n) (get e))
         sw (-> lines (get s) (get w))]
     (and (check-chars [nw se]) (check-chars [ne sw])))))

(defn count-x-mas [lines]
  (reduce
   (fn [n i]
     (+ n (reduce
           (fn [m j] (+ m (if (check-mas i j lines) 1 0)))
           0 (-> lines first count range))))
   0 (range (count lines))))

(defn solve [in] (-> in s/split-lines count-x-mas))