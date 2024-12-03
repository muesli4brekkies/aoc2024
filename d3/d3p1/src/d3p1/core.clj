(ns d3p1.core (:gen-class))
(defn -main
  [args]
  (->>
   args
   slurp
   (re-seq #"mul\(\d{1,3},\d{1,3}\)")
   (mapcat #(re-seq #"\d{1,3}" %1))
   (map read-string)
   (partition 2)
   (reduce (fn [a [l r]] (+ a (* l r))) 0)
   println))