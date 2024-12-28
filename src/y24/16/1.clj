(ns y24.16.1
  (:require
   [clojure.string :as s]))

(defn nexts [in i dir wid acc scgr res]
  (reduce
   (fn [a d]
     (let [nxti (+ i d)]
       (if (or (< res acc) (= \# (get in nxti)) (< (get scgr nxti) acc)) a
           (conj a
                 (+ acc (if (= d dir) 1 1001))
                 nxti
                 d))))
   '()
   [1 -1 wid (* -1 wid)]))

(defn solve [in]
  (let [wid  (count (take-while #(not (= % \newline)) in))
        in   (vec (s/replace (s/replace in #"\n" "") #"\." " "))
        scgr (vec (repeat (count in) ##Inf))
        strt (count (take-while #(not (= % \S)) in))]
    (loop [stack (list 1 strt 0) scgr scgr res ##Inf n 0]
      (if (empty? stack) res
          (let [[dir i acc] (take 3 stack)
                adjs (nexts in i dir wid acc scgr res)
                stack (into (drop 3 stack) (reverse adjs))]
            (if (= \E (get in i))
              (recur stack scgr (min acc res) (inc n))
              (recur stack (assoc scgr i acc) res (inc n))))))))