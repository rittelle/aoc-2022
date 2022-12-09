(ns aoc-2022-clojure.core
  (:gen-class)
  (:require
   [aoc-2022-clojure.day01 :as day01]
   [aoc-2022-clojure.day02 :as day02]
   [aoc-2022-clojure.day03 :as day03]
   [aoc-2022-clojure.day04 :as day04]
   [aoc-2022-clojure.day05 :as day05]
   [aoc-2022-clojure.day06 :as day06]
   [aoc-2022-clojure.day07 :as day07]
   [aoc-2022-clojure.day08 :as day08]
   [aoc-2022-clojure.day09 :as day09]
   [clojure.java.io :as io]))

(defn -main
  "Calculates all solutions and prints them to stdout."
  [& args]
  (day01/print-solution (slurp (io/resource "day01-input.txt")))
  (day02/print-solution (slurp (io/resource "day02-input.txt")))
  (day03/print-solution (slurp (io/resource "day03-input.txt")))
  (day04/print-solution (slurp (io/resource "day04-input.txt")))
  (day05/print-solution (slurp (io/resource "day05-input.txt")))
  (day06/print-solution (slurp (io/resource "day06-input.txt")))
  (day07/print-solution (slurp (io/resource "day07-input.txt")))
  (day08/print-solution)
  (day09/print-solution))
