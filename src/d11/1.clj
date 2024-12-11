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

(defn solve [in]
  (count (reduce (fn [res _] (flatten (map process res))) (s/split in #"\s+") (range 25))))