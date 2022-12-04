(ns aoc-2022-clojure.day04
  (:require [clojure.string :as string]))

;; Part 1

(defn read-input
  "Read the input and split it into a list of vectors that contain the ranges.."
  [input-str]
  (let [parse-range #(map parse-long (string/split % #"-"))
        parse-line #(map parse-range (string/split % #","))]
    (map parse-line (string/split-lines input-str))))

(defn is-fully-contained?
  "Returns true one range fully contains in the other."
  [[range-1 range-2]]
  (defn range-contains? [[range-1-begin range-1-end] [range-2-begin range-2-end]]
    (<= range-2-begin range-1-begin range-1-end range-2-end))
  (or (range-contains? range-1 range-2) ; the range-1 contains the range-2
      (range-contains? range-2 range-1))) ; the range-2 contains the range-1

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   (filter is-fully-contained?)
                   (count)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: In " (solution-1 input-str) " pairs, the one range fully contains the other")))

;; Part 2

(defn overlap-exists?
  "Returns true if the range-1 range is at leas partially contained in the range-2."
  [[range-1 range-2]]
  (defn overlaps-into? [[range-1-begin range-1-end] [range-2-begin range-2-end]]
    (and (>= range-1-end range-2-begin) (<= range-1-begin range-2-end)))
  (or (overlaps-into? range-1 range-2) ; the range-1 is before the range-2
      (overlaps-into? range-1 range-2))) ; the range-1 is after the range-2

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input)
                   (filter overlap-exists?)
                   (count)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: In " (solution-2 input-str) " pairs, the one range at least partially contains the other")))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 04:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
