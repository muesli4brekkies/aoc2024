(ns d16.1
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
  (let [wid  (count (take-while #(not (= % \newline)) in))
        in   (vec (s/replace (s/replace in #"\n" "") #"\." " "))
        scgr (vec (repeat (count in) ##Inf))
        strt (count (take-while #(not (= % \S)) in))]
    (loop [stack (list 1 strt 0 []) scgr scgr res ##Inf n 0]
      (if (empty? stack) res
          (let [[dir i acc seen] (take 4 stack)
                adjs (nexts in i dir wid seen acc)
                stack (into (drop 4 stack) (if (or (< res acc) (< (get scgr i) acc)) [] (reverse adjs)))]
            (if (= \E (get in i))
              (recur stack scgr (min acc res) (inc n))
              (recur stack (assoc scgr i acc) res (inc n))))))))