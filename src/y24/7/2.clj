(ns y24.7.2 (:require [y24.7.1]))

(defn solve [in] (y24.7.1/solve in * + (comp read-string str)))