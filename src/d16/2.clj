(ns d16.2
  (:require
   [clojure.string :as s]))

(defn nexts [in i dir wid seen acc]
  (reduce
   (fn [a d]
     (let [nxti (+ i d)]
       (cond
         (= \# (get in nxti))    a
         (some #(= nxti %) seen) a
         :else (conj a
                     (conj seen i nxti)
                     (+ acc (if (= d dir) 1 1001))
                     nxti
                     d))))
   '()
   [1 -1 wid (* -1 wid)]))

(defn solve [in]
  (let [r 102504
        wid  (count (take-while #(not (= % \newline)) in))
        in   (vec (s/replace (s/replace in #"\n" "") #"\." " "))
        scgr (vec (repeat (count in) ##Inf))
        strt (count (take-while #(not (= % \S)) in))]

    (loop [stack (list :e strt 0 []) scgr scgr res ##Inf p2 #{}]
      (if (empty? stack)
        (do (println
             (map #(str (apply str %) "\n") (partition wid (reduce (fn [a b] (assoc a b \*)) in p2))))
            (count p2))
        (let [[dir i acc seen] (take 4 stack)
              news (nexts in i dir wid seen acc)
              stack (into (drop 4 stack) (if (or (< r acc) (< res acc) (< (get scgr i) (- acc 1000))) [] (reverse news)))]
          (if (and (= \E (get in i)) (= r acc))
            (recur
             stack
             scgr
             (min acc res)
             (into p2 seen))
            (recur
             stack
             (assoc scgr i acc)
             res
             p2)))))))