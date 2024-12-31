(ns y15.7.1
  (:require
   [clojure.string :as s]))

(defn solve [in]
  (loop [in (s/split-lines in) res {"1" 1 "2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7 "8" 8 "9" 9 "0" 0}]
    (if (empty? in)
      (res (res "a"))
      (let [[a0 a1 a2 a3 a4] (s/split (first in) #" ")]
        (cond
          (= "->" a1)                                               (recur (next in) (assoc res a2 (if (= "a" a2) a0 (read-string     a0))))
          (and (= "NOT"    a0) (contains? res a1))                  (recur (next in) (assoc res a3 (bit-and-not     16rFFFF  (res a1))))
          (and (= "RSHIFT" a1) (contains? res a0))                  (recur (next in) (assoc res a4 (bit-shift-right (res a0) (read-string a2))))
          (and (= "LSHIFT" a1) (contains? res a0))                  (recur (next in) (assoc res a4 (bit-shift-left  (res a0) (read-string a2))))
          (and (= "AND"    a1) (every? #(contains? res %) [a0 a2])) (recur (next in) (assoc res a4 (bit-and         (res a0) (res a2))))
          (and (= "OR"     a1) (every? #(contains? res %) [a0 a2])) (recur (next in) (assoc res a4 (bit-or          (res a0) (res a2))))
          :else                                                     (recur (conj (vec (next in)) (first in)) res))))))
