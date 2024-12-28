(ns y15.1.2)

(defn solve [in] (loop [i 0 ac 0] (if (= -1 ac) i (recur (inc i) (+ ac ({\( 1 \) -1} (nth in i)))))))