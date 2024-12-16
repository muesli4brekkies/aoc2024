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
     [d13.1] [d13.2]
     [d14.1] [d14.2]
     [d15.1][d15.2]
     [d16.1] [d16.2] 
     ;
     ))

(def solvers
  {:1  {:infile "inputs/1.input"  :fn {:1 d1.1/solve  :2 d1.2/solve}}
   :2  {:infile "inputs/2.input"  :fn {:1 d2.1/solve  :2 d2.2/solve}}
   :3  {:infile "inputs/3.input"  :fn {:1 d3.1/solve  :2 d3.2/solve}}
   :4  {:infile "inputs/4.input"  :fn {:1 d4.1/solve  :2 d4.2/solve}}
   :5  {:infile "inputs/5.input"  :fn {:1 d5.1/solve  :2 d5.2/solve}}
   :6  {:infile "inputs/6.input"  :fn {:1 d6.1/solve  :2 d6.2/solve}}
   :7  {:infile "inputs/7.input"  :fn {:1 d7.1/solve  :2 d7.2/solve}}
   :8  {:infile "inputs/8.input"  :fn {:1 d8.1/solve}}
   :9  {:infile "inputs/9.input"  :fn {:1 d9.1/solve}}
   :10 {:infile "inputs/10.input" :fn {:1 d10.1/solve :2 d10.2/solve}}
   :11 {:infile "inputs/11.input" :fn {:1 d11.1/solve}}
   :12 {:infile "inputs/12.input" :fn {:1 d12.1/solve :2 d12.2/solve}}
   :13 {:infile "inputs/13.input" :fn {:1 d13.1/solve :2 d13.2/solve}}
   :14 {:infile "inputs/14.input" :fn {:1 d14.1/solve :2 d14.2/solve}}
   :15 {:infile "inputs/15.input" :fn {:1 d15.1/solve :2 d15.2/solve}}
   :16 {:infile "inputs/16.input" :fn {:1 d16.1/solve :2 d16.2/solve}}
   ;
   })

(defn -main
  [darg parg & path?]
  (if (or (nil? darg) (nil? parg)) (println "need args yo - day and part, and optional input path. eg: lein run 1 2 inputs/d1.input")
      (let [[day pt] (map keyword [darg parg])]
        (println ((-> solvers (get day) (get :fn) (get pt)) (slurp (or (first path?) (-> solvers (get day) (get :infile)))))))))