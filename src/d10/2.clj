(ns d10.2)

(def n# {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9})

(defn next-steps [in wid i]
  (reduce
   (fn [a j] (let [cur (inc (n# (get in i))) nxt (n# (get in j))] (if (= nxt cur) (conj a j) a)))
   []
   [(inc i) (+ i wid) (- i wid) (dec i)]))

(defn solver [in]
  (let [wid (inc (count (take-while #(not (= % \newline)) in)))]
    (reduce
     (fn [res i]
       (if (not (= \0 (get in i)))
         res
         (into res
               (loop [stack (list i) acc []]
                 (if (empty? stack)
                   acc
                   (let [num (first stack)
                         stack (into (next stack) (next-steps in wid num))
                         acc (if (= (get in num) \9) (conj acc num) acc)]
                     (recur stack acc)))))))
     []
     (range (count in)))))

(defn solve [in] (count (solver in)))