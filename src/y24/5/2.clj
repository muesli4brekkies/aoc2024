(ns y24.5.2 (:require [y24.5.1]))

(defn idx [l s] (reduce #(if (and (nil? %1) (= (nth l %2) s)) %2 %1) nil (-> l count range)))

(defn del [l el] (let [fore (take-while #(not (= el %)) l)] (into (vec fore) (drop (inc (count fore)) l))))

(defn ins-after [l el x] (let [i (inc (idx l el)) [pre post] (split-at i l)] (-> pre vec (into [x]) (into post))))

(defn edit-line [line [x y]] (if (apply < [(idx line x) (idx line y)]) line (-> line (ins-after x y) (del y))))

(defn correct [rules line]
  (let [linerules (y24.5.1/match-rules rules line) edited-line (reduce edit-line line linerules )]
    (if (every? (partial y24.5.1/pass-rule? edited-line) linerules)
      edited-line
      (recur linerules edited-line))))

(defn solve [in]
  (let [[rules challenge] (y24.5.1/split-input in)]
    (->>
     (filter (comp not #(y24.5.1/check rules %)) (drop 1 challenge))
     (map #(correct rules %))
     (map #(read-string (nth % (-> % count (/ 2)))))
     (apply +))))