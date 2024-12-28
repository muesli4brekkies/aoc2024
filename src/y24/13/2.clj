(ns y24.13.2)

(defn solve [in]
  (reduce
   (fn [acc [ax ay bx by tx ty]]
     (let [[tx ty] (map #(+ 10000000000000 %) [tx ty])
           c (- (* ax by) (* ay bx))
           x (/ (- (* tx by) (* ty bx)) c)
           y (/ (- (* ty ax) (* tx ay)) c)]
       (if (every? integer? [x y])
         (+ acc (+ (* 3 x) y))
         acc)))
   0
   (partition 6 (map #(Integer/parseUnsignedInt %) (re-seq #"\d+" in)))))