(ns y15.8.1&2
  (:require
   [clojure.string :as s]))

(defn solve [in]
  (let [pt2 (+ (* 2 (count (re-seq #"\n" in))) (count (re-seq #"\\|\"" in)))
        pt1 (loop [in (s/replace in #"\n" "") len 0 tot 0]
              (cond
                (nil? (first in)) (- tot len)
                (= \" (first in)) (recur (next in) len (inc tot))
                (= \\ (first in)) (let [n (if (= \x (second in)) 4 2)]
                                    (recur (drop n in) (inc len) (+ n tot)))
                :else             (recur (next in) (inc len) (inc tot))))]
    [pt1 pt2]))
