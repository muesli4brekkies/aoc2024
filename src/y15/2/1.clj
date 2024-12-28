(ns y15.2.1 (:require [clojure.string :as s]))

(defn solve [in]
  (loop [in (s/split in #"\n|x") ac 0]
    (if (empty? in)
      ac
      (recur (drop 3 in) (+ ac (let [[a b c] (map read-string (take 3 in))
                                     [ab bc ac] [(* 2 a b) (* 2 b c) (*  2 a c)]]
                                 (+ ab bc ac (bit-shift-right (min ab bc ac) 1))))))))
