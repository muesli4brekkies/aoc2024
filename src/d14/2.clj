(ns d14.2)

(defn solve [in]
  (map #(str % \newline)
       (loop [i 10000]
         (let [botmap
               (reduce
                (fn [acc [x y dx dy]]
                  (let [[rx ry] (map (fn [[n d o]] (-> d neg? (if (+ d o) d) (* i) (+ n) (mod o))) [[x dx 101] [y dy 103]])]
                    (assoc acc ry (assoc (get acc ry) rx \E))))
                (->> \. (repeat 101) vec (repeat 103) vec)
                (->> in (re-seq #"-?\d+") (map #(Integer/parseInt %)) (partition 4)))]
           (if (some (fn [line] (re-find #"EEEEEEEE" (apply str line))) botmap)
             botmap
             (recur (dec i)))))))