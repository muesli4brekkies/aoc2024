(ns core
  (:require
   [d1.1]
   [d1.2]
   [d2.1]
   [d2.2]
   [d3.1]
   [d3.2]))

(def inputs
  ["inputs/d1.input"
   "inputs/d2.input"
   "inputs/d3.input"])
(def fns
  [[d1.1/solve d1.2/solve]
   [d2.1/solve d2.2/solve]
   [d3.1/solve d3.2/solve]])

(defn grab [fn args] (-> args fn read-string (- 1)))

(defn -main
  [& args]
  (if (> 2 (count args))
    (println "need args yo - day and part eg lein run 1 2")
    (println ((-> fns (get (grab first args)) (get (grab second args))) (get inputs (grab first args))))))
