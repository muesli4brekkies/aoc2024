(ns d6.1)

(defn solve [in]
  (->>
   (let [inv (vec in)
         wid (->> inv (take-while #(not (= % \newline))) count inc)
         start (count (take-while #(not (= % \^)) inv))]
     (loop [res inv
            i start
            step (* -1 wid)]
       (let [next (get in (+ step i))
             e-res (assoc res i \x)]
         (if (or (nil? next) (= \newline next))
           e-res
           (if (= next \#)
             (recur e-res i (if (= step 1) wid (if (= step wid) -1 (if (= step -1) (* -1 wid) 1))))
             (recur e-res (+ i step) step))))))
   frequencies
   (#(get % \x))))