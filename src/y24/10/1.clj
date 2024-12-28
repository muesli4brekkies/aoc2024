(ns y24.10.1)

(def n# {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9})

(defn next-steps [in wid i]
  (reduce
   (fn [a j] (let [cur (inc (n# (get in i))) nxt (n# (get in j))] (if (= nxt cur) (conj a j) a)))
   []
   [(inc i) (+ i wid) (- i wid) (dec i)]))

(defn wander [in wid]
  (fn [res i]
    (if (not (= \0 (get in i)))
      res
      (+ res
         (count
          (set
           (loop [stack (list i) acc []]
             (if (empty? stack)
               acc
               (let [num (first stack)
                     stack (into (next stack) (next-steps in wid num))
                     acc (if (= (get in num) \9) (conj acc num) acc)]
                 (recur stack acc))))))))))

(defn solve [in]
  (let [wid (inc (count (take-while #(not (= % \newline)) in)))]
    (reduce (wander in wid) 0 (range (count in)))))