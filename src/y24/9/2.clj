(ns y24.9.2)

(defn solve [in]
  (let [n# {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9 \newline 0}
        alloc (first (reduce (fn [[r i n] b] [(conj r (repeat (n# b) (if (odd? i) nil n))) (inc i) (if (odd? i) n (inc n))]) [[] 0 0] in))
  ]
    (prn alloc)
    (loop [res [] in alloc ni (reverse alloc)]
      (if (empty? ni) 
        res
        (let [[n m] [(first in) (first ni)]]
          (if (< (count n) (count m)) 
            (recur res in (next ni))
            ()
            ))))))