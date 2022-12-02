(ns aoc-2022-clojure.core
  (:gen-class)
  (:require
   [aoc-2022-clojure.day01 :as day01]
   [aoc-2022-clojure.day02 :as day02]
   [clojure.java.io :as io]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (day01/print-solution (slurp (io/resource "day01-input.txt")))
  (day02/print-solution (slurp (io/resource "day02-input.txt"))))
