(ns y24.25.1 (:require [clojure.string :as s]))

(defn solve [in]
  (first
   (reduce
    (fn [[acc [lcks keys]] g]
      (let [h (map #(count (re-seq #"#" (apply str %))) (apply map vector (s/split-lines g)))
            l? (s/starts-with? g "#####")
            kl (if l? [(conj lcks h) keys] [lcks (conj keys h)])
            fits (+ acc (reduce (fn [ac c] (if (every? #(<= % 7) (map #(+ %1 %2) c h)) (inc ac) ac)) 0 (if l? keys lcks)))]
        [fits kl]))
    [0 [[] []]]
    (s/split in #"\n\n"))))