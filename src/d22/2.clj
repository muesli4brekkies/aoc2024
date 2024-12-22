(ns d22.2
  (:require
   [clojure.math :as m]
   [clojure.string :as s]))

(defn mixnprune [s f a] (let [n (f s a)] (mod (bit-xor n s) 16777216)))

(defn- loopadoop [in res j]
  (let [nums (loop [i 0 n (first in) last4r [(mod n 10)] last4d [] rmap {}]
               (if (= 2000 i)
                 rmap
                 (let [r (-> n (mixnprune * 64) (mixnprune m/floor-div 32) (mixnprune * 2048))
                       <4l (= 4 (count last4d))
                       l4d (conj (vec (if <4l (next last4d) last4d)) (- (mod r 10) (or (last last4r) 0)))
                       l4r (conj (vec (if <4l (next last4r) last4r)) (mod r 10))
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
