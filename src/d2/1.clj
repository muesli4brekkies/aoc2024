(ns d2.1)
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

(defn solve
  [path]
  (->>
   path
   slurp
   s/split-lines
   (map #(s/split %1 #"\s+"))
   (map #(map read-string %1))
   (map #(diffs %1 []))
   (filter isvalid?)
   count))