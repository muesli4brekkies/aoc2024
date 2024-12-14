(ns d14.1 (:require [clojure.math :as m]))

(def wid 101)
(def hgt 103)
(def hwid (m/floor-div wid 2))
(def hhgt (m/floor-div hgt 2))

(defn- tally [acc q] (if q (assoc acc q (inc (get acc q))) acc))

(defn solve [in]
  (->>
   in
   (re-seq #"-?\d+")
   (map #(Integer/parseInt %))
   (partition 4)
   (reduce
    (fn [acc [x y dx dy]]
      (let [[dx dy] [(if (neg? dx) (+ wid dx) dx) (if (neg? dy) (+ hgt dy) dy)]
            [rx ry] [(mod (+ x (* 100 dx)) wid) (mod (+ y (* 100 dy)) hgt)]]
        (tally acc (cond (> hhgt ry) (cond (< hwid rx) :ne (> hwid rx) :nw)
                         (< hhgt ry) (cond (< hwid rx) :se (> hwid rx) :sw)))))
    {:ne 0 :nw 0 :se 0 :sw 0})
   vals
   (apply *)))