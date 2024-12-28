(ns y15.2.2 (:require [clojure.string :as s]))

(defn solve [in]
  (loop [in (s/split in #"\n|x") ac 0]
    (if (empty? in)
      ac
      (recur (drop 3 in) (+ ac (let [[a b c] (sort (map read-string (take 3 in)))]
                                 (+ a a b b (* a b c))))))))