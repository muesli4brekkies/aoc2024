(ns y15.12.1&2 (:require [clojure.data.json :as j]))

(defn dig [o]
  (cond
    (vector? o) (map dig o)
    (number? o) o
    (string? o) 0
    (some #(= "red" %) (vals o)) 0
    :else (map dig (vals o))))

(defn solve [in] [(apply + (map #(Integer/parseInt %) (re-seq #"-?\d+" in))) (apply + (flatten (dig (j/read-str in))))])