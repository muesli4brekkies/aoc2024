(ns y24.1.2 (:require [clojure.string :as s]))

(defn solve [in]
  (let [nums (map-indexed vector (map read-string (s/split in #"\s+|\n")))
        [l r] (reduce (fn [[l r] [i b]] (if (zero? (mod i 2)) [(conj l b) r] [l (conj r b)])) [[] []] nums)]
    (reduce #(+ %1 (* %2 (or (get (frequencies r) %2) 0))) 0 l)))