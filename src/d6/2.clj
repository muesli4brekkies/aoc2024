(ns d6.2 (:require [d6.1]))

(defn try-loop [ingrid start sdir rturn dirchars]
  (loop [grid ingrid
         i start
         dir sdir]
    (let [dirc  (dirchars dir)
          curr  (get grid i)
          nxti  (+ dir i)
          nxtc  (get grid nxti)
          pgrid (assoc grid i (conj (if (vector? curr) curr []) dirc))]
      (cond
        (and (vector? nxtc)
             (some #(= dirc %) nxtc)) 1
        (or (nil? nxtc)
            (= \newline nxtc))        0
        (= \# nxtc)                   (recur pgrid i (rturn dir))
        :else                         (recur pgrid nxti dir)))))

(defn mapify [v1 v2] (into {} (map (fn [x y] [x y]) v1 v2)))

(defn solve [in]
  (let [in    (vec in)
        wid   (inc (count (take-while #(not (= % \newline)) in)))
        dirs  [(* -1 wid) 1 wid -1]
        dircs (mapify dirs [:N :E :S :W])
        rturn (mapify dirs (map dirs [1 2 3 0]))
        start (count (take-while #(not (= % \^)) in))]
    (loop [grid in
           i    start
           acc  0
           dir  (first dirs)]
      (let [dirc  (dircs dir)
            curr  (get grid i)
            nxti  (+ dir i)
            nxtc  (get grid nxti)
            pgrid (assoc grid i (conj (if (vector? curr) curr []) dirc))
            acc   (if (vector? nxtc) acc (+ acc (try-loop (assoc grid nxti \#) i dir rturn dircs)))]
        (cond
          (or (nil? nxtc)
              (= \newline nxtc)) acc
          (= \# nxtc)            (recur pgrid i acc (rturn dir))
          :else                  (recur pgrid nxti acc dir))))))