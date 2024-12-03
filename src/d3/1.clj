(ns d3.1)
(defn solve
  [args]
  (->>
   args
   slurp
   (re-seq #"mul\(\d{1,3},\d{1,3}\)")
   (mapcat #(re-seq #"\d{1,3}" %1))
   (map read-string)
   (partition 2)
   (reduce (fn [a [l r]] (+ a (* l r))) 0)))