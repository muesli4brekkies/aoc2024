(ns y24.9.1)

(defn solve [in]
  (let [n# {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9 \newline 0}
        alloc (first (reduce (fn [[r i n] b] [(into r (repeat (n# b) (if (odd? i) nil n))) (inc i) (if (odd? i) n (inc n))]) [[] 0 0] in))]
    (->>
     (loop [res (vec alloc) i 0 j (dec (count alloc))]
       (cond
         (= i j)            (take (inc i) res)
         (nth res i)        (recur res (inc i) j)
         (nil? (nth res j)) (recur res i (dec j))
         :else              (recur (assoc res i (nth res j)) i (dec j))))
     (map-indexed vector)
     (map #(apply * %))
     (apply +))))