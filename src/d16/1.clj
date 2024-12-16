(ns d16.1
  (:require
   [clojure.string :as s]))

(defn nexts [in i dirs dir seen acc]
  (reduce
   (fn [a ind]
     (let [nxti (+ i (dirs ind))]
       (cond
         (= \# (get in nxti))    a
         (some #(= nxti %) seen) a
         :else (conj a
                     (conj seen i nxti)
                     (+ acc (if (= ind dir) 1 1001))
                     nxti
                     ind))))
   '()
   (keys dirs)))

(defn solve [in]
  (let [wid  (count (take-while #(not (= % \newline)) in))
        dirs {:n (* -1 wid) :s wid :w -1 :e 1}
        in   (vec (s/replace (s/replace in #"\n" "") #"\." " "))
        scgr (vec (repeat (count in) ##Inf))
        strt (count (take-while #(not (= % \S)) in))]

    (loop [stack (list :e strt 0 []) scgr scgr res ##Inf n 0]
      (if (empty? stack)
        res
        (let [[dir i acc seen] (take 4 stack)
              adjs (nexts in i dirs dir seen acc)]
          (cond
            (= \E (get in i))      (recur
                                    (drop 4 stack)
                                    scgr
                                    (if (< acc res)
                                      (do (prn res acc n) acc) res) (inc n))
            (or
             (< res acc)
             (< (get scgr i) acc)) (recur
                                    (drop 4 stack)
                                    scgr
                                    res
                                    (inc n))
            :else                  (recur
                                    (into (drop 4 stack) (reverse adjs))
                                    (assoc scgr i acc)
                                    res
                                    (inc n))))))))