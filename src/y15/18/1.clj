(ns y15.18.1)

(defn nbs [i g wid]
  (let [n (or ((frequencies (map #(nth g (+ i %) nil) [1 (+ 1 wid) wid (+ -1 wid) -1 (+ (* -1 wid) -1) (* -1 wid) (+ (* -1 wid) 1)])) \#) 0)]
    (if (= \# (nth g i))
      (if (< 1 n 4) \# \.)
      (if (= 3 n) \# \.))))

(defn solve [in & n]
  (println (apply str in))
  (Thread/sleep 50)
  (let [len (count in)
        in (vec in)
        n (or n 0)
        wid (inc (count (take-while #(not (= \newline %)) in)))
        corners [0 (- wid 2) (- len 2) (- len wid) ]]
    (if (= n 100)
      ((frequencies in) \#)
      (recur
       (loop [i 0 res []]
         (if (= i len)
           res
           (let [c (nth in i)
                 r (cond 
                     (= c \newline) \newline
                     (some #(= i %) corners) \#
                     :else (nbs i in wid))]

             (recur (inc i) (conj res r)))))
       (inc n)))))