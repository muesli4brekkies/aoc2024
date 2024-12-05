(ns d3.2 (:require [d3.1] [clojure.string :as s]))

(defn solve [in] (-> in (s/replace #"(?s)don't\(\).+?do\(\)" "") d3.1/solver))