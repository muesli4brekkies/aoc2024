(ns d16.2
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
  (let [r 102504
        wid  (count (take-while #(not (= % \newline)) in))
        dirs {:n (* -1 wid) :s wid :w -1 :e 1}
        in   (vec (s/replace (s/replace in #"\n" "") #"\." " "))
        scgr (vec (repeat (count in) ##Inf))
        strt (count (take-while #(not (= % \S)) in))]

    (loop [stack (list :e strt 0 []) scgr scgr res ##Inf n 0 p2 #{}]
      (if (empty? stack)
        (do (println
             (map #(str (apply str %) "\n") (partition wid (reduce (fn [a b] (assoc a b \*)) in p2))))
            (count p2))
        (let [[dir i acc seen] (take 4 stack)
              news (nexts in i dirs dir seen acc)]
          (cond
            (and
             (= \E (get in i))
             (= r acc))        (recur
                                (drop 4 stack)
                                scgr
                                (if (< acc res)
                                  (do (prn res acc n) acc) res)
                                (inc n)
                                (into p2 seen))
            (or
             (< r acc)
             (< res acc)
             (< (get scgr i)
                (- acc 1000))) (recur
                                (drop 4 stack)
                                scgr
                                res
                                (inc n)
                                p2)
            :else              (recur
                                (into (drop 4 stack) (reverse news))
                                (assoc scgr i acc)
                                res
                                (inc n)
                                p2)))))))