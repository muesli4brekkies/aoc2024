(ns y15.6.2)

(defn slice [in x y]
  (let [[x y] (map read-string [x y])
        [pre rst] (split-at x in)
        [res pst] (split-at (- (inc y) x) rst)]
    [pre res pst]))

(defn- go [in]
  (let [g (repeat 1000 (repeat 1000 0))]
    (reduce
     (fn [res [c xx yx xy yy]]
       (let [switch ({"of" #(if (zero? %) % (dec %)) "on" #(inc %) "to" #(+ 2 %)} c)
             [pre lines post] (slice res xx xy)
             mlines (map #(let [[pre seg post] (slice % yx yy)]
                            (into (vec pre) (into (vec (map switch seg)) post)))
                         lines)]
         (into (vec pre) (into (vec mlines) post))))
     g
     in)))

(defn solve [in] (->> in (re-seq #"\d+|on|of|to") (partition 5) go flatten (apply +)))