(ns y24.24.1 (:require [clojure.string :as s]))

(defn solve [in]
  (let [fns {"AND" bit-and "XOR" bit-xor "OR" bit-or}
        [xy cmds] (map #(s/split-lines %) (s/split in #"\n\n"))
        xy (reduce (fn [ac e] (let [[w b] (s/split e #": ")] (assoc ac w (read-string b)))) {} xy)
        bin (reduce
             (fn [a [_ b]] (str a b))
             ""
             (sort
              (fn [[a _] [b _]] (compare a b))
              (filter
               (fn [[w _]] (s/includes? w "z"))
               (vec
                (loop [cmds cmds res xy]
                  (if (empty? cmds)
                    res
                    (let [cmd (first cmds)
                          [a f b _ r] (s/split cmd #" ")]
                      (if (and (contains? res a) (contains? res b))
                        (recur  (next cmds) (assoc res r ((fns f) (res a) (res b))))
                        (recur (conj (vec (next cmds)) cmd) res)))))))))]
    (reduce
     (fn [a i]
       (+ a (* (Integer/parseUnsignedInt (str (nth bin i))) (bit-shift-left 1 i))))
     0
     (range (count bin)))))