(ns y15.3.2)

(defn- follow [in]
  (first
   (reduce
    (fn [[r l] n]
      (let [d {\< [-1 0] \> [1 0] \^ [0 -1] \v [0 1]}
            q (map (fn [a b] (+ a b)) l (d n))]
        [(conj r q) q])) [[] [0 0]] in)))

(defn solve [in]
  (let [[i n]
        (reduce
         (fn [[i n] j]
           (if (odd? j)
             [(conj i (nth in j)) n]
             [i (conj n (nth in j))]))
         [[] []]
         (range (count in)))
        res (into (into #{[0 0]} (follow i)) (follow n))]
    (count res)))