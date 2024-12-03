(ns d3.2)
(require '[clojure.string :as s])

(defn solve
  [args]
  (->>
   args
   slurp
   (#(s/replace %1 #"(?s)don't\(\).+?(do\(\)|$)" ""))
   (re-seq #"mul\(\d{1,3},\d{1,3}\)")
   (mapcat #(->> (re-seq #"\d{1,3}" %1) (map read-string)))
   (partition 2)
   (reduce (fn [a [l r]] (+ a (* l r))) 0)))