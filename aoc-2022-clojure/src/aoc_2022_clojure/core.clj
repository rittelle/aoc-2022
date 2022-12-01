(ns aoc-2022-clojure.core
  (:require [aoc-2022-clojure.day01.solution :as day01]
            [clojure.java.io :as io])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (day01/print-solution (slurp (io/resource "day01-input.txt"))))
