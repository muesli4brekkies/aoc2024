(defproject aocd2p2 "0.1"
  :description "Advent of Code Day 2 Part 2"
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot d2p2.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
