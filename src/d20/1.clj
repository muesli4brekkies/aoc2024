(ns d20.1
  (:require
   [clojure.string :as s]))

(defn solve [in]
  (let [wid (count (take-while #(not (= \newline %)) in))
        in (vec (s/replace in #"\n" ""))
        st (count (take-while #(not (= \S %)) in))
        dirs [1 -1 wid (* -1 wid)]
        path (loop [i st acc 0 res {}]
               (if (nil? i)
                 res
                 (recur
                  (reduce
                   (fn [a j]
                     (if (or (= (nth in j nil) \#) (contains? res j))
                         a
                         j))
                   nil
                   (map #(+ i %) dirs))
                  (inc acc)
                  (assoc res i acc))))]
    (reduce
     (fn [acc i]
       (+ acc
          (reduce
           (fn [ac j]
             (let [n (path i)
                   m (get path (+ i j j)  0)]
               (if (<= 100 (- m n 2))
                 (inc ac)
                 ac)))
           0
           dirs)))
     0
     (keys path))))


