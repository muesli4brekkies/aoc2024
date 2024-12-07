(ns d7.2 (:require [d7.1]))

(defn solve [in] (d7.1/solve in * + (comp read-string str)))