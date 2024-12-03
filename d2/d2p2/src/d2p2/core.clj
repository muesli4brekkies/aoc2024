(ns d2p2.core
  (:gen-class))
(require '[clojure.string :as s])

(defn extract [blob] (->> blob s/split-lines (map #(s/split %1 #"\s+")) (map #(map read-string %1))))

(defn is-ordered [line] (some #(apply %1 line) [< >]))

(defn deltas-in-range [line]
  (every? #(> 4 (abs %1)) (for [i (-> line count (- 1) range)] (- (nth line i) (nth line (inc i))))))

(defn check [line] (and (deltas-in-range line) (is-ordered line)))

(defn check-opts [line] (some #(check (concat (take %1 line) (drop (inc %1) line))) (range (count line))))

(defn validate [lines] (filter #(or (check %1) (check-opts %1)) lines))

(defn -main [path] (->> path slurp extract validate count println))