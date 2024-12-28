(ns y24.22.1
  (:require
   [clojure.math :as m]
   [clojure.string :as s]))

(defn mixnprune [s f a] (let [n (f s a)] (mod (bit-xor n s) 16777216)))

(defn- loopadoop [in]
  (loop [i 2000 n (first in)]
    (if (zero? i)
      n
      (recur
       (dec i)
       (-> n
           (mixnprune * 64)
           (mixnprune m/floor-div 32)
           (mixnprune * 2048))))))

(defn solve [in]
  (let [in (map read-string (s/split-lines in))]
    (loop [in in acc 0]
      (if (empty? in)
        acc
        (recur (next in) (+ acc (loopadoop in)))))))