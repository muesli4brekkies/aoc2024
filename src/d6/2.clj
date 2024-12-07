(ns d6.2 (:require [d6.1]))

(defn solve [in] (->> in d6.1/walk-route second (map (comp d6.1/walk-route #(assoc (vec in) % \#))) (filter not) count))