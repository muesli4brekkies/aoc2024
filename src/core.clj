(ns core (:gen-class)
    (:require
     [y15.1.1] [y15.1.2]
     [y15.2.1] [y15.2.2]
     [y15.3.1] [y15.3.2]

     [y15.5.1] [y15.5.2]

     [y24.1.1] [y24.1.2]
     [y24.2.1] [y24.2.2]
     [y24.3.1] [y24.3.2]
     [y24.4.1] [y24.4.2]
     [y24.5.1] [y24.5.2]
     [y24.6.1] [y24.6.2]
     [y24.7.1] [y24.7.2]
     [y24.8.1] [y24.8.2]
     [y24.9.1] [y24.9.2]
     [y24.10.1] [y24.10.2]
     [y24.11.1];[y24.11.2]
     [y24.12.1] [y24.12.2]
     [y24.13.1] [y24.13.2]
     [y24.14.1] [y24.14.2]
     [y24.15.1] [y24.15.2]
     [y24.16.1] [y24.16.2]
     [y24.17.1] [y24.17.2]
     [y24.18.1] [y24.18.2]
     ;[y24.19.1] [y24.19.2]
     [y24.20.1] ;[y24.20.2]
     ;[y24.21.1] [y24.21.2]
     [y24.22.1] [y24.22.2]
     ;[y24.23.1] [y24.23.2]
     [y24.24.1] ;[y24.24.2]
     [y24.25.1] ;[y24.25.2]
     ))

(def solvers
  {:15 {:1  {:1 y15.1.1/solve  :2 y15.1.2/solve}
        :2  {:1 y15.2.1/solve  :2 y15.2.2/solve}
        :3  {:1 y15.3.1/solve  :2 y15.3.2/solve}

        :5  {:1 y15.5.1/solve  :2 y15.5.2/solve
        }
        }

   :24 {:1  {:1 y24.1.1/solve  :2 y24.1.2/solve}
        :2  {:1 y24.2.1/solve  :2 y24.2.2/solve}
        :3  {:1 y24.3.1/solve  :2 y24.3.2/solve}
        :4  {:1 y24.4.1/solve  :2 y24.4.2/solve}
        :5  {:1 y24.5.1/solve  :2 y24.5.2/solve}
        :6  {:1 y24.6.1/solve  :2 y24.6.2/solve}
        :7  {:1 y24.7.1/solve  :2 y24.7.2/solve}
        :8  {:1 y24.8.1/solve  :2 y24.8.2/solve}
        :9  {:1 y24.9.1/solve  :2 y24.9.2/solve}
        :10 {:1 y24.10.1/solve :2 y24.10.2/solve}
        :11 {:1 y24.11.1/solve}
        :12 {:1 y24.12.1/solve :2 y24.12.2/solve}
        :13 {:1 y24.13.1/solve :2 y24.13.2/solve}
        :14 {:1 y24.14.1/solve :2 y24.14.2/solve}
        :15 {:1 y24.15.1/solve :2 y24.15.2/solve}
        :16 {:1 y24.16.1/solve :2 y24.16.2/solve}
        :17 {:1 y24.17.1/solve}
        :18 {:1 y24.18.1/solve :2 y24.18.2/solve}
        ;:19 {:1 y24.19.1/solve}
        :20 {:1 y24.20.1/solve}
        ;:21 {:1 y24.21.1/solve}
        :22 {:1 y24.22.1/solve :2 y24.22.2/solve}
        ;:23 {:1 y24.23.1/solve}
        :24 {:1 y24.24.1/solve}
        :25 {:1 y24.25.1/solve}}})

(defn -main
  [yarg darg parg & path?]
  (if (some #(nil? %) [yarg darg parg]) (println "need args yo - year, day and part, and optional input path. eg: lein run 24 1 2 inputs/d1.input")
      (let [[year day pt] (map keyword [yarg darg parg])]
        (println ((-> solvers (get year) (get day) (get pt)) (slurp (or (first path?) (str "src/y" yarg "/in/" darg ".in"))))))))