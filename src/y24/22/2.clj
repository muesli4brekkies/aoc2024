(ns y24.22.2
  (:require
   [clojure.math :as m]
   [clojure.string :as s]))

(defn mixnprune [s f a] (let [n (f s a)] (mod (bit-xor n s) 16777216)))

(defn- loopadoop [in res j]
  (let [nums (loop [i 0 n (first in) l4r [(mod n 10)] l4d [] rmap {}]
               (if (= 2000 i)
                 rmap
                 (let [r (-> n (mixnprune * 64) (mixnprune m/floor-div 32) (mixnprune * 2048))
                       append (fn [l o] (conj (vec (if (= 4 (count l)) (next l) l)) (- (mod r 10) o)))
                       l4d (append l4d (last l4r))
                       l4r (append l4r 0)
                       rmap (if (and (= 4 (count l4d)) (not (contains? rmap l4d)))
                              (assoc rmap l4d (mod r 10))
                              rmap)]
                   (recur (inc i) r l4r l4d rmap))))]
    (reduce (fn [res [k v]] (if-let [el (get res k)] (assoc res k (+ v el)) (assoc res k v))) res nums)))

(defn solve [in]
  (apply max
         (map second
              (let [in (map read-string (s/split-lines in))]
                (loop [in in res {} j 0]
                  (if (empty? in)
                    res
                    (recur (next in) (loopadoop in res j) (inc j))))))))
