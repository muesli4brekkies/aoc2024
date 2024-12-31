(ns y15.6.1)

(defn slice [in x y]
  (let [[x y] (map read-string [x y])
        [pre rst] (split-at x in)
        [res pst] (split-at (- (inc y) x) rst)]
    [pre res pst]))

(defn- go [in]
  (let [g (repeat 1000 (repeat 1000 false))]
    (reduce
     (fn [res [c xx yx xy yy]]
       (let [switch ({"of" #(and % false) "on" #(or % true) "to" not} c)
             [pre lines post] (slice res xx xy)
             mlines (map #(let [[pre seg post] (slice % yx yy)]
                            (into (vec pre) (into (vec (map switch seg)) post)))
                         lines)]
         (into (vec pre) (into (vec mlines) post))))
     g
     in)))

(defn solve [in] (->> in (re-seq #"\d+|on|of|to") (partition 5) go flatten (filter true?) count))