(ns aoc-2022-clojure.day01
  "day 01 solution"
  (:require [clojure.string :as string])
  (:import [java.lang Integer]))

;; Part 1

(defn read-input
  "Splits an input string into a sequence where each element contains a vector
  corresponding to the weights that an elf is carrying."
  [input-str]
  (let [read-elf (fn [s] (map #(Integer/parseUnsignedInt %) (string/split s #"\n")))
        read-elves (fn [s] (map read-elf (string/split s #"\n\n")))]
    (read-elves input-str)))

(defn calculate-all-elf-calories
  "Return the number of calories carried by a list of elves."
  [elves] (map (partial reduce +) elves))

(defn solution-1
  "Solve the first part with the given input."
  [input-str] (->> input-str
                   (read-input)
                   (calculate-all-elf-calories)
                   (reduce max)))

(defn print-solution-1
  "Print a message containing the solution to the first part to stdout."
  [input-str] (println (str "  Part A: An elf carries " (solution-1 input-str) " calories which is the most.")))

;; Part 2

(defn get-top-n-elves
  "Find the n calories counts from the elves that carry the most calories."
  [n elves] (->> elves
                 (sort)
                 (reverse)
                 (take n)))

(defn solution-2
  "Solve the second part with the given input."
  [input-str] (->> input-str
                   (read-input)
                   (calculate-all-elf-calories)
                   (get-top-n-elves 3)
                   (reduce +)))

(defn print-solution-2
  "Print a message containing the solution to the second part to stdout."
  [input-str] (println (str "  Part B: Three elves carry " (solution-2 input-str) " calories together.")))

;; Print both parts

(defn print-solution
  "Print a message containing the solution to the complete exercise to stdout."
  [input-str]
  (println "Day 01:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
