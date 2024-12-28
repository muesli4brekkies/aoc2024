(ns y15.5.1
  (:require
   [clojure.string :as s]))

(defn solve [in]
  (count
   (filter true?
           (for [line (s/split-lines in)]
             (and (< 2 (count (re-seq #"a|e|i|o|u" line)))
                  (seq (re-seq #"(\w)\1" line))
                  (not (seq (re-seq #"ab|cd|pq|xy" line))))))))