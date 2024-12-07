(ns d6.1)

(defn walk-route [in]
  (let [in (vec in)
        wid (->> in (take-while #(not (= % \newline))) count inc)
        start (count (take-while #(not (= % \^)) in))
        dirs (assoc
              {}
              (keyword (str (* -1 wid))) :N
              (keyword (str 1))          :E
              (keyword (str (* wid)))    :S
              (keyword (str -1))         :W)]
    (loop [res in i start step (* -1 wid) seeni []]
      (let [seeni (conj seeni i)
            dir (get dirs (keyword (str step)))
            curr (get res i)
            res (assoc res i (assoc (if (map? curr) curr {}) dir true))
            next (get res (+ step i))]
        (if (get curr dir)
          false
          (if (or (= next \newline) (nil? next))
            [res (set seeni)]
            (if (= \# next)
              (recur res i (if (= step 1) wid (if (= step wid) -1 (if (= step -1) (* -1 wid) 1))) seeni)
              (recur res (+ i step) step seeni))))))))

(defn solve [in] (count (filter map? (first (walk-route in)))))