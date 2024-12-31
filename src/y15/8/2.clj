(ns y15.8.2
  (:require
   [clojure.string :as s]))

(defn solve [in]
  (let [stm (s/replace in #"\n" "")]
    (+ (* -1 (count stm))
       (* 2  (count (s/split-lines in)))
       (reduce #(+ %1 (if (or (= %2 \") (= %2 \\)) 2 1)) 0 stm))))