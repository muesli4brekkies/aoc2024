(ns d14.2)

(defn solve [in]
  (map #(str % \newline)
       (let [wid 101 hgt 103
             blank (->> \. (repeat wid) vec (repeat hgt) vec)
             bots (->> in (re-seq #"-?\d+") (map #(Integer/parseInt %)) (partition 4))]
         (loop [i 10000]
           (let [botmap
                 (reduce
                  (fn [acc [x y dx dy]]
                    (let [[rx ry] (map (fn [[n d o]] (-> d neg? (if (+ d o) d) (* i) (+ n) (mod o))) [[x dx wid] [y dy hgt]])]
                      (assoc acc ry (assoc (get acc ry) rx \E))))
                  blank
                  bots)]
             (if (some (fn [line] (re-find #"EEEEEEEE" (apply str line))) botmap)
               botmap
               (recur (dec i))))))))