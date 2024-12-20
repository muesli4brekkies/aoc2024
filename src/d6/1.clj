(ns d6.1)

(defn solve [in]
  (let [in    (vec in)
        wid   (inc (count (take-while #(not (= % \newline)) in)))
        dirs  [(* -1 wid) 1 wid -1]
        rturn (into {} (map (fn [x y] [x y]) dirs (map dirs [1 2 3 0])))]
    (loop [in in
           i  (count (take-while #(not (= % \^)) in))
           ac 0
           d  (* -1 wid)]
      (let [grid (assoc in i \x)
            j  (+ d i)
            c  (get in j)]
        (cond
          (= \x c) (recur grid j ac d)
          (= \. c) (recur grid j (inc ac) d)
          (= \# c) (recur grid i ac (rturn d))
          :else (inc ac))))))