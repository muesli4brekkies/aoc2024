(ns d7.2 (:require [clojure.string :as s]))

(defn calc [[t x y & rest]]
  (if (nil? y) (= x t) (when (<= x t) (some #(calc (conj rest % t)) (map #(% x y) [* + (comp read-string str)])))))

(defn solve [in]
  (->> in s/split-lines (map #(map read-string (s/split % #": | "))) (reduce (fn [a b] (if (calc b) (+ a (first b)) a)) 0)))