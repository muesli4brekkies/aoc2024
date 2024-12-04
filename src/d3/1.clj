(ns d3.1 (:require [clojure.string :as s]))

(defn solver [str] (->> str (re-seq #"mul\(\d{1,3},\d{1,3}\)") (reduce #(+ %1 (->> (s/split %2 #"[^0-9]") (filter (comp not empty?)) (map read-string) (apply *))) 0)))

(defn solve [args] (->> args slurp solver))
