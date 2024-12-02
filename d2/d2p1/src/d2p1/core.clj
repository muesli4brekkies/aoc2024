(ns d2p1.core
  (:gen-class))

(require '[clojure.string :as s])

(defn diffs
  [line res]
  (if (= 1 (count line))
    res
    (recur (drop 1 line) (conj res (- (first line) (second line))))))

(defn isvalid?
  [line]
  (and (every? #((if (< 0 (first line)) < >) 0 %1) line)
       (every? #(and (>= 3 (abs %1)) (not (= %1 0))) line)))

(defn -main
  []
  (->>
   "../input.txt"
   (slurp)
   (s/split-lines)
   (map #(s/split %1 #"\s+"))
   (map #(map read-string %1))
   (map #(diffs %1 []))
   (filter isvalid?)
   (count)
   (println)))

(remove #{1} [1 1 2 1 3 4])