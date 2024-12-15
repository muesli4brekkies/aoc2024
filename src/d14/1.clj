(ns d14.1)

(defn- tally [acc q] (if q (assoc acc q (inc (get acc q))) acc))

(defn solve [in]
  (->>
   in
   (re-seq #"-?\d+")
   (map #(Integer/parseInt %))
   (partition 4)
   (reduce
    (fn [acc [x y dx dy]]
      (let [[rx ry] (map (fn [[n d o]] (-> d neg? (if (+ d o) d) (* 100) (+ n) (mod o))) [[x dx 101] [y dy 103]])]
        (tally acc (cond (> 51 ry) (cond (< 50 rx) :ne (> 50 rx) :nw) (< 51 ry) (cond (< 50 rx) :se (> 50 rx) :sw)))))
    {:ne 0 :nw 0 :se 0 :sw 0})
   vals
   (apply *)))