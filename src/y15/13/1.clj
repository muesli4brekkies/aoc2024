(ns y15.13.1
  (:require [clojure.math.combinatorics :as c]
            [clojure.string :as s]))

(defn solve [in]
  (let [peeps (distinct (re-seq #"[A-Z][a-z]*" in))
        rels (reduce
              #(let [[ee _ gl num _ _ _ _ _ _ er] (s/split %2 #" |\.")
                     num (* (if (= gl "lose") -1 1) (Integer/parseInt num))]
                 (assoc %1 ee (assoc (%1 ee) er num)))
              (apply hash-map (interleave peeps (repeat (count peeps) {})))
              (s/split-lines in))]
    (reduce
     (fn [ac rte]
       (max
        ac
        (reduce
         (fn [ac [fr to]]
           (+ ac ((rels to) fr) ((rels fr) to)))
         0
         (partition 2 1 (conj rte (last rte))))))
     0
     (c/permutations peeps))))

