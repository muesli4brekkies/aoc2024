(ns core (:gen-class)
    (:require
     [d1.1] [d1.2]
     [d2.1] [d2.2]
     [d3.1] [d3.2]
     [d4.1] [d4.2]
     [d5.1] [d5.2]
     [d6.1] [d6.2]
     [d7.1] [d7.2]
     [d8.1]
     [d9.1]
     [d10.1] [d10.2]
     [d11.1]
     [d12.1] [d12.2]
     [d13.1] [d13.2]))
     ;


(def solvers
  {:1  ["inputs/1.input"  {:1 d1.1/solve  :2 d1.2/solve}]
   :2  ["inputs/2.input"  {:1 d2.1/solve  :2 d2.2/solve}]
   :3  ["inputs/3.input"  {:1 d3.1/solve  :2 d3.2/solve}]
   :4  ["inputs/4.input"  {:1 d4.1/solve  :2 d4.2/solve}]
   :5  ["inputs/5.input"  {:1 d5.1/solve  :2 d5.2/solve}]
   :6  ["inputs/6.input"  {:1 d6.1/solve  :2 d6.2/solve}]
   :7  ["inputs/7.input"  {:1 d7.1/solve  :2 d7.2/solve}]
   :8  ["inputs/8.input"  {:1 d8.1/solve}]
   :9  ["inputs/9.input"  {:1 d9.1/solve}]
   :10 ["inputs/10.input" {:1 d10.1/solve :2 d10.2/solve}]
   :11 ["inputs/11.input" {:1 d11.1/solve}]
   :12 ["inputs/12.input" {:1 d12.1/solve :2 d12.2/solve}]
   :13 ["inputs/13.input" {:1 d13.1/solve :2 d13.2/solve}]
   ;
   })



(defn -main
  [darg parg & path?]
  (if (or (nil? darg) (nil? parg))
    (println "need args yo - day and part, and optional input path. eg: lein run 1 2 inputs/d1.input")
    (let [[d p] (map keyword [darg parg])]
      (println ((-> solvers (get d) (get 1) (get p)) (slurp (or (first path?) (-> solvers (get d) (get 0)))))))))