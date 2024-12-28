(ns y15.1.1)

(defn solve [in] (reduce #(+ %1 ({\( 1 \) -1} %2)) 0 in))
