(ns d7.1 (:require [clojure.string :as s]))

(defn calc [x [t & puz]]
  (let [n (first puz)]
    (if (empty? puz)
      (= x t)
      (when (<= x t) (some #(calc % (->> puz (drop 1) (into [t]))) [(* (if (zero? x) 1 x) n) (+ x n)])))))

(defn solve [in]
  (->>
   in
   s/split-lines
   (map #(map read-string (s/split % #": | ")))
   (filter (partial calc 0))
   (map first)
   (apply +)))