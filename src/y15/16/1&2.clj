(ns y15.16.1&2)

(defn solve [in]
  (let [p2 false
        isue {"children" "3" "cats" "7" "samoyeds" "2" "pomeranians" "3" "akitas" "0" "vizslas" "0" "goldfish" "5" "trees" "3" "cars" "2" "perfumes" "1"}]
    (loop [i 1 sues (map #(apply hash-map %) (partition 6 (re-seq #"(?<= )[a-z]+|(?<=: )\d+" in)))]
      (let [sue (first sues)]
        (if (every?
             #(or
               (nil? (sue %))
               (when p2
                 (and (or (= % "cats") (= % "trees")) (apply > (map read-string [(sue %) (isue %)])))
                 (and (or (= % "pomeranians") (= % "goldfish"))  (apply < (map read-string [(sue %) (isue %)]))))
               (= (isue %) (sue %)))
             (keys isue))
          i
          (recur (inc i) (next sues)))))))