(ns y24.8.1
  (:require
   [clojure.string :as s]))

(defn find-nodes [nant stream acc ant]
  (let [[a- a+] (sort [ant nant])
        d (abs (- nant ant))
        [i+ i-] [(+ a+ d) (- a- d)]
        [n+ n-] [(nth stream i+ nil) (nth stream i- nil)]]
    (reduce
     (fn [a [n i]] (if (nil? n) a (conj a i)))
     acc
     [[n+ i+] [n- i-]])))

(defn solve [in]
  (let [lines (s/split-lines in)
        wid (count (first lines))
        nils (repeat wid nil)
        stream (flatten (map seq (drop-last (interleave (s/split in #"\n") (repeat (count lines) nils)))))
        len (count stream)]
    (loop [i 0 res #{} rec {}]
      (if (= i len)
        (count res)
        (let [c (nth stream i)
              kc (keyword (str c))
              rec? (rec kc)]
          (if (or (nil? c) (= c \.))
            (recur (inc i) res rec)
            (if rec?
              (recur
               (inc i)
               (into res (reduce (partial find-nodes i stream) #{} rec?))
               (assoc rec kc (conj rec? i)))
              (recur (inc i) res (assoc rec kc [i])))))))))

