(ns d5.2 (:require [d5.1]))

(defn idx [l s] (reduce #(if (and (nil? %1) (= (nth l %2) s)) %2 %1) nil (-> l count range)))

(defn del [l el] (let [i (idx l el)] (concat (take i l) (drop (inc i) l))))

(defn ins-after [l el x] (let [i (inc (idx l el))] (conj (concat (take i l) [x] (drop i l)))))

(defn edit-line [line [x y]]
  (let [[ix iy] [(idx line x) (idx line y)]]
    (if (< ix iy)
      line
      (-> line (ins-after x y) (del y)))))

(defn correct [rules line]
  (let [rules (d5.1/match-rules rules line)
        edited-line (reduce edit-line line rules)]
    (if (every? (partial d5.1/pass-rule? edited-line) rules)
      edited-line
      (recur rules edited-line))))

(defn solve [in]
  (let [[rules challenge] (d5.1/split-input in)]
    (->>
     (filter (comp not #(d5.1/check rules %)) (drop 1 challenge))
     (map (partial correct rules))
     (map #(read-string (nth % (-> % count (/ 2)))))
     (apply +))))