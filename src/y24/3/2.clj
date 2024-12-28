(ns y24.3.2 (:require [y24.3.1] [clojure.string :as s]))

(defn solve [in] (-> in (s/replace #"(?s)don't\(\).+?do\(\)" "") y24.3.1/solver))