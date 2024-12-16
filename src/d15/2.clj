(ns d15.2
  (:require
   [clojure.string :as s]
   [clojure.math :as m]))

(defn push [grid dir i]
  (let [line (loop [i i res []]
               (let [c (nth grid i)]
                 (if (= c \#)
                   res
                   (recur (+ i dir) (conj res c)))))]
    (if (some #(= \. %) line)
      (let [pre (take-while (fn [c] (not (= c \.))) line)
            new (into (vec (conj pre \.)) (drop (inc (count pre)) line))]
        [(+ dir i) (reduce (fn [res j] (assoc res (+  i (* j dir)) (get new j))) grid (range (count new)))])
      [i grid])))

(defn look [grid dir i]
  (loop [stack (list i) res #{}]
    (if (empty? stack)
      res
      (let [nxti (+ dir (first stack))
            nxtc (nth grid nxti)
            res (conj res nxti)]
        (cond
          (= nxtc \#) nil
          (= nxtc \[) (recur (conj (next stack) nxti (inc nxti)) res)
          (= nxtc \]) (recur (conj (next stack) nxti (dec nxti)) res)
          :else (recur (next stack) res))))))

(defn pusv [grid dir i]
  (if
   (= \. (nth grid (+ i dir))) [(+ i dir) (assoc (assoc grid i \.) (+ i dir) \@)]
   (if-let [tree (look grid dir i)]
     [(+ i dir)
      (reduce
       (fn [gr ni] (assoc (assoc gr ni (get gr (- ni dir))) (- ni dir) \.))
       grid
       (if (pos? dir) (sort > tree) (sort tree)))]
     [i grid])))

(defn gobot [dirs]
  (fn [cmds [i grid]]
    (let [cmd (first cmds)]
      (cond
        (nil? cmds) grid
        (or (= cmd \<) (= cmd \>)) (recur (next cmds) (push grid (dirs cmd) i))
        (or (= cmd \v) (= cmd \^)) (recur (next cmds) (pusv grid (dirs cmd) i))))))

(defn calc [in wid]
  (reduce (fn [a i]
            (if (= \[ (get in i))
              (+ a (mod i wid) (* 100 (m/floor-div i wid)))
              a)) 0 (range (count in))))

(defn solve [in]
  (let [wid    (* 2 (count (take-while #(not (= % \newline)) in)))
        [grid
         cmds] (map #(s/replace % #"\n" "") (s/split in #"\n\n"))
        grid   (vec (reduce (fn [a [r s]] (s/replace a r s))
                            (apply str grid)
                            [[#"\." ".."] [#"O" "[]"] [#"#" "##"] [#"@" "@."]]))
        start  (count (take-while #(not (= % \@)) grid))
        dirs   {\> 1 \< -1 \v wid \^ (* -1 wid)}]
    (calc ((gobot dirs) cmds [start grid]) wid)))