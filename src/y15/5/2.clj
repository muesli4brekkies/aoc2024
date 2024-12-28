(ns y15.5.2
  (:require
   [clojure.string :as s]))

(defn solve [in]
  (count
   (filter seq
           (for [line (s/split-lines in)]
             (and (seq (re-seq #"(\w\w).*\1" line))
                  (seq (re-seq #"(\w)\w\1" line)))))))