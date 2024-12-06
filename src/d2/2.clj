(ns d2.2 (:require [clojure.string :as s]))

(defn extract [in] (->> in (map #(s/split % #"\s+")) (map #(map read-string %))))

(defn okranges? [line]
  (every? #(> 4 (abs %)) (for [i (-> line count (- 1) range)] (- (nth line i) (nth line (inc i))))))

(defn pass? [line] (and (okranges? line) (some #(apply % line) [< >])))

(defn try-choose-n [line] (some #(pass? (concat (take % line) (drop (inc %) line))) (range (count line))))

(defn check [lines] (filter #(or (pass? %) (try-choose-n %)) lines))

(defn solve [in] (-> in s/split-lines extract check count))