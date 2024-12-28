(ns y24.17.1 (:require [clojure.math :as m]))

(defn solve [in]
  (let [v vector
        [a b c & t] (map read-string (re-seq #"\d+" in))
        combo (fn [n [a b c]] (if-let [r ({4 a 5 b 6 c} n)] r n))]
    (loop [[a b c i r] [a b c 0 []]]
      (let [ops (fn [[n x]]
                  ([#(v (m/floor-div a (m/pow 2 (combo x [a b c]))) b c (+ 2 i) r)
                    #(v a (bit-xor b x) c (+ 2 i) r)
                    #(v a (mod (combo x [a b c]) 8) c (+ 2 i) r)
                    #(v a b c (if (zero? a) (+ 2 i) x) r)
                    #(v a (bit-xor b c) c (+ 2 i) r)
                    #(v a b c (+ 2 i) (conj r (mod (combo x [a b c]) 8)))
                    #(v a (m/floor-div a (m/pow 2 (combo x [a b c]))) c (+ 2 i) r)
                    #(v a b (m/floor-div a (m/pow 2 (combo x [a b c]))) (+ 2 i) r)] n))]
        (if (= i (count t)) r (recur ((ops (take 2 (drop i t))))))))))