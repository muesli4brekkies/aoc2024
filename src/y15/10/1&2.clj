(ns y15.10.1&2)

(defn solve [in & n]
  (if (= n 50)
    (count in)
    (do (when (= n 40) (prn (count in)))
        (recur
         (apply
          str
          (reduce
           #(into %1 [(count (first %2)) (second %2)])
           []
           (re-seq #"(\d)\1*" in)))
         (if (nil? n) 1 (inc n))))))