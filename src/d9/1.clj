(ns d9.1 (:require [clojure.string :as s]))

(defn alloc [[in len]]
  (loop [i 0 x 0 numnils 0 res []]
    (if (= i len)
      [res numnils]
      (let [n (- (int (nth in i)) 48)]
        (if (zero? (mod i 2))
          (recur (inc i)
                 (inc x)
                 numnils
                 (into res (repeat n x)))
          (recur (inc i)
                 x
                 (+ n numnils)
                 (into res (repeat n nil))))))))

(defn stack [[in numnils]]
  (let [len (-> in count (- numnils))
        tail (->> in (take-last numnils) (filter number?) reverse)]
    (loop [i 0 res 0 ltail tail]
      (if (= i len)
        res
        (if (nil? (nth in i))
          (recur (inc i)
                 (-> ltail first (* i) (+ res))
                 (drop 1 ltail))
          (recur (inc i)
                 (-> in (nth i) (* i) (+ res))
                 ltail))))))

(defn solve [in] (-> in s/trim-newline (#(list % (count %))) alloc stack))