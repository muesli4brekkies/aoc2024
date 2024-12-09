(ns d8.1
  (:require
   [clojure.string :as s]))

(defn walk-line [acc d i f stream]
  (if (nil? (nth stream (f i d) nil))
    acc
    (recur (conj acc (f i d)) d (f i d) f stream)))

(defn find-nodes [nant stream acc ant]
  (let [[a- a+] (sort [ant nant])
        diff (abs (- nant ant))
        [i+ i-] [(+ a+ diff) (- a- diff)]
        [n+ n-] [(nth stream i+ nil) (nth stream i- nil)]]
    (reduce
     (fn [a [n i f]] (if (nil? n) a (walk-line (conj a i) diff i f stream)))
     acc
     [[n+ i+ +] [n- i- -]])))

(defn solve [in]
  (let [lines (s/split-lines in)
        wid (count (first lines))
        nils (repeat wid nil)
        stream (flatten (map seq (drop-last (interleave (s/split in #"\n") (repeat (count lines) nils)))))
        len (count stream)]
    (loop [i 0 res #{} rec {}]
      (if (= i len)
        [(let [s (reduce (fn [a m] (into a (filter (comp not nil?) (assoc (vec stream) m "*"))))[] res)]
           (reduce (fn [a i] (str a (get stream i) (if (= i wid) "q" ""))) "" (range (count s))) nil)

          ;)
         (count res)]
        (let [c (nth stream i)
              kc (keyword (str c))
              rec? (rec kc)]
          (if (or (nil? c) (= c \.))
            (recur (inc i) res rec)
            (if rec?
              (recur
               (inc i)
               (into res (reduce (partial find-nodes i stream) #{i} rec?))
               (assoc rec kc (conj rec? i)))
              (recur (inc i) res (assoc rec kc [i])))))))))


(filter nil? [nil nil 1 2 3 nil nil])