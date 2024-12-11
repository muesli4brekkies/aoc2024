(ns d10.2)

(def cint {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9})

(defn next-steps [in wid i]
  (reduce
   (fn [a j] (let [c (cint (get in j))] (if (=  c (inc (cint (get in i)))) (conj a j) a)))
   []
   [i (- i wid) (inc i) (dec i) (+ i wid)]))

(defn testn [in wid]
  (fn [res i]
    (if (not (= \0 (get in i)))
      res
      (+ res
         (loop [stack (list i) acc 0]
           (if (empty? stack)
             acc
             (let [num (first stack)]
               (recur
                (into (next stack) (next-steps in wid num))
                (if (= (get in num) \9) (inc acc) acc)))))))))


(defn solve [in] (let [wid (inc (count (take-while #(not (= % \newline)) in)))]
                   (reduce (testn in wid) 0 (range (count in)))))
