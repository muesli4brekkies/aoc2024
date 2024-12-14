(ns d14.2)

(def wid 101)
(def hgt 103)

(defn solve [in]
  (let [blank (->> \. (repeat wid) vec (repeat hgt) vec)
        bots (->> in (re-seq #"-?\d+") (map #(Integer/parseInt %)) (partition 4))]
    (->> (for [i (range 10000)]
           (reduce
            (fn [acc [x y dx dy]]
              (let [[dx dy] [(if (neg? dx) (+ wid dx) dx) (if (neg? dy) (+ hgt dy) dy)]
                    [ex ey] [(mod (+ x (* i dx)) wid) (mod (+ y (* i dy)) hgt)]]
                (assoc acc ey (assoc (get acc ey) ex \E))))
            blank
            bots))
         (reduce
          (fn [[n a i] mp]
            (let [ecount
                  (reduce
                   (fn [a line]
                     (+ a (apply max (map count (if-let [l (re-seq #"E+" (apply str line))] l [""])))))
                   0 mp)]
              (if (< n ecount)
                [ecount [i mp] (inc i)]
                [n a (inc i)])))
          [0 [0 []] 0])
         ((fn [[_ [i mp]]] [(map #(conj % \newline) mp) "\n" i])))))