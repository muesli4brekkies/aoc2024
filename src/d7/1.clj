(ns d7.1 (:require [clojure.string :as s]))

(defn calc [[t x y & rest] fns]
  (if (nil? y) (= x t) (when (<= x t) (some #(calc (conj rest % t) fns) (map #(% x y) fns)))))

(defn solve [in & fns]
  (->> in s/split-lines (map #(map read-string (s/split % #": | "))) (reduce (fn [a b] (if (calc b (if fns fns [* +])) (+ a (first b)) a)) 0)))