(ns d11.1
  (:require
   [clojure.string :as s]))

(defn process [nstr]
  (if (= "0" nstr)
    ["1"]
    (let [nstr (str "" nstr)]
      (if (zero? (bit-and 1 (count nstr)))
        (map (fn [n] (->> n (apply str) Integer/parseUnsignedInt str)) (split-at (/ (count nstr) 2) nstr))
        [(str (* 2024 (read-string nstr)))]))))

(defn blink [nums] (flatten (map process nums)))

(defn solve [in]
  (reduce (fn [res n] [(dec n) (blink res)]) (s/split in #"\s+") (range 25))
  (let [nums (s/split in #"\s+")]
    (count
     (loop [n 25 lnums nums]
       (if (zero? n)
         lnums
         (recur (dec n) (blink lnums)))))))