(ns y24.18.2
  (:require
   [clojure.math :as m]
   [clojure.string :as s]))

(defn makemap [in wid nbyte]
  (let [grid  (vec (repeat (* wid wid) \.))
        in    (map read-string (re-seq #"\d+" in))]
    (loop [i 0 grid grid]
      (if (= i (* 2 nbyte))
        [grid (vec (repeat (* wid wid) ##Inf))]
        (let [[x y] (map #(nth in %) [i (inc i)])] (recur (+ 2 i) (assoc grid (+ x (* y wid)) \#)))))))

(defn- news [bmap i wid acc smap]
  (let [dirs (map #(+ i %) [(* -1 wid) -1 1 wid])]
    (reduce
     (fn [a j]
       (let [c (nth bmap j nil)]
         (if (or
              (nil? c)
              (= \# c)
              (and (= 1 (abs (- i j))) (not (= (m/floor-div i wid) (m/floor-div j wid))))
              (< (or (nth smap j nil) ##Inf) acc))
           a
           (conj a [j (inc acc)]))))
     []
     dirs)))

(defn- path [in n]
  (let [wid 71
        tgt (dec (* wid wid))
        [bmap smap] (makemap in wid n)]
    (loop [stack [[0 0 []]] smap smap res ##Inf]
      (if (empty? stack)
        res
        (let [[i acc] (first stack)]
          (if (= i tgt)
            (recur (drop 3 stack) (assoc smap i acc) (min acc res))
            (let [new (news bmap i wid acc smap)
                  stack (distinct (into (vec (next stack)) new))]
              (recur
               stack
               (assoc smap i acc)
               res))))))))

(defn solve [in]
  (let [lines (s/split in #"\n")]
    (loop [n 1024 o 100]
      (let [res (path in (+ n o))]
        (if (= ##Inf res)
          (if (= o 1)
            (nth lines  n)
            (recur n (/ o 10)))
          (recur (+ n o) o))))))

