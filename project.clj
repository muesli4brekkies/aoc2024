(defproject aocd1p1 "0.1"
  :description "Advent of Code Day - in Clojure"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/math.combinatorics "0.3.0"]]
  :main ^:skip-aot core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
