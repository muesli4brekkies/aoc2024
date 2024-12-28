(ns y24.3.1)

(defn solver [str] (->> str (re-seq #"mul\(\d{1,3},\d{1,3}\)") (reduce #(+ %1 (->> (re-seq #"\d+" %2) (map read-string) (apply *))) 0)))

(defn solve [in] (->> in solver))
