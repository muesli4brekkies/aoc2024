(ns y15.11.1&2)

(defn incstr [nums]
  (let [nums (conj (vec (drop-last nums)) (inc (last nums)))]
    (->>
     (loop [i (dec (count nums)) res nums]
       (cond
         (= i 0) res
         (= (nth res i) 123) (recur (dec i) (assoc (assoc (vec res) i 97) (dec i) (inc (nth res (dec i)))))
         :else (recur (dec i) res)))
     (map char)
     (apply str))))

(defn solve [in & lp]
  (let [nums (map int in)]
    (if
     (and
      (not (re-matches #"o|i|l" in))
      (loop [nums nums]
        (cond
          (= (count nums) 2) false
          (let [[a b c] (take 3 nums)] (and (= a (dec b)) (= b (dec c)))) true
          :else (recur (next nums))))
      (loop [nums nums ac 0]
        (cond
          (= ac 2) true
          (empty? nums) false
          (let [[a b] (take 2 nums)] (= a b)) (recur (drop 2 nums) (inc ac))
          :else (recur (next nums) ac))))
      (if lp in (do (println in) (recur (incstr nums) true)))
      (recur (incstr nums) lp))))
