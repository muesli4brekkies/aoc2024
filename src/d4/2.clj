(ns d4.2 (:require [clojure.string :as s]))

(defn check-mas [i j lines]
  (if (and
       (= (-> lines (get i) (get j)) \A)
       (let [[n w s e] [(dec i) (dec j) (inc i) (inc j)]]
         (and
          (let [nw (-> lines (get n) (get w))
                se (-> lines (get s) (get e))]
            (or (and (= nw \M) (= se \S)) (and (= nw \S) (= se \M))))
          (let [ne (-> lines (get n) (get e))
                sw (-> lines (get s) (get w))]
            (or (and (= ne \M) (= sw \S)) (and (= ne \S) (= sw \M)))))))
    1
    0))

(defn count-x-mas [lines]
  (reduce
   (fn [n i]
     (+ n (reduce
           (fn [m j] (+ m (check-mas i j lines)))
           0 (-> lines first count range))))
   0 (range (count lines))))

(defn solve [path]
  (->
   path
   slurp
   s/split-lines
   count-x-mas))