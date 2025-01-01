(ns y15.1.1)

(defn solve [in] (reduce #(({\( inc \) dec} %2) %1) 0 in))
