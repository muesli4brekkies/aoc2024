(ns core
  (:require
   [d1.1] [d1.2]
   [d2.1] [d2.2]
   [d3.1] [d3.2]
   [d4.1] [d4.2]))

(def solvers
  [["inputs/d1.input" [d1.1/solve d1.2/solve]]
   ["inputs/d2.input" [d2.1/solve d2.2/solve]]
   ["inputs/d3.input" [d3.1/solve d3.2/solve]]
   ["inputs/d4.input" [d4.1/solve d4.2/solve]]])

(defn -main
  [darg parg & path?]
  (if (or (nil? darg) (nil? parg))
    (println "need args yo - day and part eg lein run 1 2")
    (let [[d p] [(dec (read-string darg)) (dec (read-string parg))]
          inp (slurp (or path? (-> solvers (get d) (get 0))))
          fnc (-> solvers (get d) (get 1) (get p))]
          (println ( fnc inp)))))