(require '[clojure.string :as str])
(require '[clojure.core.reducers :as reducers])

(def input (slurp "../input.txt.bak"))


(defn diff [left right])

(defn -main
  []
  (print
   (let [[left right]
         (reduce
          (fn [[lacc racc] line]
            (let [[l r] (map read-string (clojure.string/split line #"\s+"))]
              [(conj lacc l) (conj racc r)]))
          [[] []]
          (clojure.string/split input #"\n"))]
     (reduce + (map (fn [[l r]] (abs (- l r))) (partition 2 (interleave (sort left) (sort right))))))))


