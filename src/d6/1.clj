(ns d6.1)

(defn solve [in]
  (let [in    (vec in)
        wid   (inc (count (take-while #(not (= % \newline)) in)))
        dirs  [(* -1 wid) 1 wid -1]
        rturn (into {} (map (fn [x y] [x y]) dirs (map dirs [1 2 3 0])))]
    (loop [in in
           ac 0
           d  (* -1 wid)
           i  (count (take-while #(not (= % \^)) in))]
      (let [i  (+ d i)
            c  (get in i)
            grid (assoc in i \x)]
        (cond
          (= \x c) (recur grid i ac d)
          (= \. c) (recur grid i (inc ac) d)
          (= \# c) (recur grid i ac (rturn d))
          :else (inc ac))))))