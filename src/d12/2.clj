(ns d12.2)

(defn- countcorners [in dirs j c]
  (let [[u l d r]     (map #(nth in % nil) (map #(+ j %) dirs))
        [ul ld dr ru] (map #(nth in % nil) (map (fn [x y] (+ j x y)) dirs (map dirs [1 2 3 0])))
        corner?       (fn [[x y xy]] (if (or (not (or (= c x) (= c y))) (and (= c x) (= c y) (not (= c xy)))) 1 0))]
    (apply + (map #(corner? %) [[u l ul] [l d ld] [d r dr] [r u ru]]))))

(defn- plot-plot [in i c dirs seen res]
  (loop [stk (list i) plot [] corners 0]
    (if (empty? stk)
      [in (assoc seen c (if-let [q (seen c)] (into q plot) plot)) (+ res (* (count plot) corners))]
      (let [j (first stk)
            new (filter
                 (fn [n]
                   (and
                    (= (nth in n nil) c)
                    (not (some #(= % n) plot))))
                 (map #(+ % j) dirs))]
        (recur
         (distinct (into (next stk) new))
         (conj plot j)
         (+ corners (countcorners in dirs j c)))))))

(defn- plot-plots [in len dirs]
  (loop [i 0 [in seen res] [in {} 0]]
    (let [c (nth in i nil)]
      (cond
        (= len i) res
        (some #(= % i) (seen c)) (recur (inc i) [in seen res])
        (= c \newline) (recur (inc i) [in seen res])
        :else
        (recur (inc i) (plot-plot in i c dirs seen res))))))

(defn solve [in]
  (let [in (vec in)
        wid (inc (count (take-while #(not (= \newline %)) in)))
        dirs [(* -1 wid) -1 wid 1]
        len (count in)]
    (plot-plots in len dirs)))