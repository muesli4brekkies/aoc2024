(ns core
  (:require
   [d1.1] [d1.2]
   [d2.1] [d2.2]
   [d3.1] [d3.2]
   [d4.1] [d4.2]
   [d5.1] [d5.2]
   [d6.1] [d6.2]
   )
  ( :gen-class))

(def solvers
  [["inputs/1.input" [d1.1/solve d1.2/solve]]
   ["inputs/2.input" [d2.1/solve d2.2/solve]]
   ["inputs/3.input" [d3.1/solve d3.2/solve]]
   ["inputs/4.input" [d4.1/solve d4.2/solve]]
   ["inputs/5.input" [d5.1/solve d5.2/solve]]
   ["inputs/6.input" [d6.1/solve d6.2/solve]]
   ])

(defn -main
  [darg parg & path?]
  (if (or (nil? darg) (nil? parg))
    (println "need args yo - day and part, and optional input path. eg: lein run 1 2 inputs/d1.input")
    (let [[d p] [(dec (read-string darg)) (dec (read-string parg))]
          inp (slurp (or (first path?) (-> solvers (get d) (get 0))))
          fnc (-> solvers (get d) (get 1) (get p))]
      (prn (fnc inp)))))