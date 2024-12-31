(ns y15.4.1 
  (:require
    [clojure.string :as s]))

(import 'java.security.MessageDigest
        'java.math.BigInteger)

(defn md5 [^String s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        raw (.digest algorithm (.getBytes s))]
    (format "%032x" (BigInteger. 1 raw))))

(defn solve [in]
  (loop [n 0 ] 
    (let [hash (md5 (str in n))]
      (if (s/starts-with? hash "00000")
        n
        (recur (inc n))))))