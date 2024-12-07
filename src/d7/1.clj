(ns d7.1 (:require [clojure.string :as s]))

(defn calc [[t & puz] & ns]
  (let [x (first puz) [n m] (#(if (nil? %) [0 1] [% %]) (first ns))]
    (if (empty? puz) (= n t)
        (when (<= n t)
          (some #(calc (->> puz (drop 1) (into [t])) %) [(* m x) (+ n x)])))))

(defn solve [in]
  (->>
   in
   s/split-lines
   (map #(map read-string (s/split % #": | ")))
   (filter calc)
   (map first)
   (apply +)))