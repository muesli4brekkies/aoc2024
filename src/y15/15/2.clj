(ns y15.15.2)

(defn solve [in]
  (reduce
   (fn
     [ac q]
     (max
      ac
      (first
       (reduce
        (fn [[ac cal] i]
          (let [m (apply + (map #(* %1 %2) q i))]
            (cond
              (nil? cal) [1 m]
              (< 500 cal) [0 cal]
              :else [(* ac (max m 0)) cal])))
        [1 nil]
        (reverse
         (apply
          map
          vector
          (partition
           5
           (map
            #(Integer/parseInt %)
            (re-seq #"-?\d+" in)))))))))
   0
   (loop
    [i 1 res []]
     (if (= i 100)
       res
       (recur
        (inc i)
        (into
         res
         (loop
          [j 1 res []]
           (if (< 100 (+ i j))
             res
             (recur
              (inc j)
              (into
               res
               (loop
                [k 1 res []]
                 (if (< 100 (+ i j k))
                   res
                   (recur
                    (inc k)
                    (conj
                     res
                     [i j k (- 100 (+ i j k))]))))))))))))))