(ns y24.8.2
  (:require
   [clojure.math :as m]
   [clojure.string :as s]))

(defn- gradient [wid i j]
  (apply / (map #(apply - %) (apply map vector (map (fn ([n] [(m/floor-div n wid) (mod n wid)])) [i j])))))

(defn- traceline [i wid len sames]
  (reduce
   (fn [ac j]
     (into
      ac
      (let [m (gradient wid i j)
            c (- (m/floor-div i wid) (* m (mod i wid)))]
        (reduce
         (fn [a x]
           (let [y (+ c (* m x))]
             (if (and (integer? y) (< -1 y (/ len wid)))
               (conj a (+ x (* wid y)))
               a)))
         []
         (range wid)))))
   []
   sames))

(defn solve [in]
  (let [wid (count (take-while #(not (= % \newline)) in))
        in  (s/replace in #"\n" "")
        len (count in)]
    (loop [i    0
           ants {}
           acc  []]
      (let [c (nth in i nil)]
        (cond
          (nil? c) (count (set acc))
          (or (= c \newline) (= c \.)) (recur (inc i) ants acc)
          :else (if-let [sames (get ants c nil)]
                  (recur
                   (inc i)
                   (assoc ants c (conj sames i))
                   (into acc (traceline i wid len sames)))
                  (recur
                   (inc i)
                   (assoc ants c [i])
                   acc)))))))