(ns y24.12.1)

(defn get-adj [i in wid] (map #(let [c (get in %)] (if (nil? c) [nil nil] [% c])) [(inc i) (dec i) (- i wid) (+ i wid)]))

(defn split-adj [adj seen ch]
  (reduce
   (fn [[nexts n] [i c]]
     (cond
       (some #(= i %) seen) [nexts n]
       (= ch c)             [(conj nexts i) n]
       :else                [nexts (inc n)]))
   [[] 0]
   adj))

(defn walk-plot [grid wid ch i]
    (loop [lgrid  grid
           seen   []
           stack  (list i)
           fences 0
           area   0]
      (let [i      (first stack)
            grid   (assoc lgrid i "x")
            [nexts
             nfen] (split-adj (get-adj i lgrid wid) seen ch)
            stack  (into (set (next stack)) nexts)]
        (if (empty? stack)
          [grid (* (+ fences nfen) (inc area))]
          (recur grid (conj seen i) stack (+ fences nfen) (inc area))))))

(defn solve [in]
  (let [wid (inc (count (take-while #(not (= % \newline)) in)))
        len (count in)]
    (loop [grid (vec in)
           i 0
           acc 0]
      (if (= i len)
        acc
        (let [ch (get grid i)]
          (if (or (= ch "x") (= ch \newline))
            (recur grid (inc i) acc)
            (let [[grid n] (walk-plot grid wid ch i)]
              (recur grid (inc i) (+ acc n)))))))))