(ns y15.3.1)

(defn solve[i](count(set(reduce #(conj %1(map(fn[a b](+ a b))(last %1)({\<[-1 0]\>[1 0]\^[0 -1]\v[0 1]}%2)))[[0 0]]i))))