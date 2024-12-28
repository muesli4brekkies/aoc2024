(ns y24.15.1
  (:require
   [clojure.string :as s]
   [clojure.math :as m]))

(defn look [i dir grid]
  (loop [i i res []]
    (let [c (nth grid i)
          res (conj res c)]
      (if (or (= c \.) (= c \#)) res (recur (+ i dir) res)))))

(defn push [line grid i dir]
  (let [fore (take-while (fn [c] (not (= c \.))) line)
        aft (drop (inc (count fore)) line)
        new (into (vec (conj fore \.)) aft)]
    (reduce (fn [res j] (assoc res (+ i (* j dir)) (get new j))) grid (range (count new)))))

(defn gobot [dirs]
  (fn [grid i cmds]
    (if-let [cmd (first cmds)]
      (let [dir (dirs cmd)
            line (look i dir grid)]
        (if (some (fn [c] (= c \.)) line)
          (recur (push line grid i dir) (+ i dir) (next cmds))
          (recur grid i (next cmds))))
      grid)))

(defn calc [in wid]
  (reduce (fn [a i]
            (if (= \O (get in i))
              (+ a (mod i wid) (* 100 (m/floor-div i wid)))
              a)) 0 (range (count in))))

(defn solve [in]
  (let [wid    (count (take-while #(not (= % \newline)) in))
        [grid
         cmds] (map (comp vec #(s/replace % #"\n" "")) (s/split in #"\n\n"))
        start  (count (take-while #(not (= % \@)) grid))
        dirs   {\> 1 \< -1 \v wid \^ (* -1 wid)}]
    (calc ((gobot dirs) grid start cmds) wid)))