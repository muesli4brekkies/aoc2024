(ns y15.14.2 (:require [clojure.math :as m]))

(defn solve [in]
  (apply
   max
   (vals
    (frequencies
     (flatten
      (let [deer (map-indexed vector (partition 3 (map read-string (re-seq #"\d+" in))))]
        (for [i (range 1 2504)]
          (loop [top 0 deer deer res []]
            (if (empty? deer)
              res
              (let [[j [v t r]] (first deer)
                    dist (+ (* v t (m/floor-div i (+ t r))) (* v (min t (rem i (+ t r)))))]
                (cond
                  (= top dist) (recur top (next deer) (conj res j))
                  (< top dist) (recur dist (next deer) [j])
                  :else        (recur top (next deer) res))))))))))))