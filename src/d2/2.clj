(ns d2.2)
(require '[clojure.string :as s])

(defn extract [in] (->> in (map #(s/split %1 #"\s+")) (map #(map read-string %1))))

(defn okranges? [line]
  (every? #(> 4 (abs %1)) (for [i (-> line count (- 1) range)] (- (nth line i) (nth line (inc i))))))

(defn pass? [line] (and (okranges? line) (some #(apply %1 line) [< >])))

(defn try-choose-n [line] (some #(pass? (concat (take %1 line) (drop (inc %1) line))) (range (count line))))

(defn check [lines] (filter #(or (pass? %1) (try-choose-n %1)) lines))

(defn solve [in] (-> in s/split-lines extract check count))