(ns d2p2.core
  (:gen-class))

(require '[clojure.string :as s])


(defn extract [blob]
  (->>
   blob
   (s/split-lines)
   (map #(s/split %1 #"\s+"))
   (map #(map read-string %1))))

(defn is-ordered [line] (some #(apply %1 line) [< >]))

(defn deltas-in-range [line]
  (->>
   (for [i (-> line (count) (- 1) (range))] (- (nth line i) (nth line (inc i))))
   (every? #(> 4 (abs %1)))))

(defn check-opts [line]
  (->>
   (reduce #(conj %1 (concat (take %2 line) (drop (inc %2) line))) [] (range (count line)))
   (some #(and (deltas-in-range %1) (is-ordered %1)))))

(defn validate [line] (-> line (deltas-in-range) (and (is-ordered line)) (or (check-opts line))))

(defn -main [path]
  (->>
   path
   (slurp)
   (extract)
   (filter validate)
   (count)
   (println)))