(ns y15.14.1 (:require [clojure.math :as m]))

(defn solve [in]
  (apply
   max
   (map
    (fn [[v t r]] (+ (* v t (m/floor-div 2503 (+ t r))) (* v (min t (rem 2503 (+ t r))))))
    (partition 3 (map read-string (re-seq #"\d+" in))))))