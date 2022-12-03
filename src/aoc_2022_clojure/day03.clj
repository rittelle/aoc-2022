(ns aoc-2022-clojure.day03
  (:require [clojure.string :as string]
            [clojure.set :as set]))

;; Part 1

(defn read-input-1
  "Read the input and split it into a list of pairs."
  [input-str] (map #(split-at (/ (count %) 2) %) (string/split-lines input-str)))

(defn get-common-items
  "Get the common items of a pair (rucksack content)."
  [contents] (reduce set/intersection (map set contents)))

(defn get-priority
  "Get the assigned priority of an item (a character)."
  [item] (let [a (int \a)
               z (int \z)
               A (int \A)
               Z (int \Z)
               i (int item)] (cond
               (<= a i z) (- i a -1)
               (<= A i Z) (- i A -27))))

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input-1)
                   (mapcat get-common-items)
                   (map get-priority)
                   (reduce +)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The total priority of the incorrectly packed items is " (solution-1 input-str))))

;; Part 2

(defn read-input-2
  "Read the input and split it into a list of lists representing the groups."
  [input-str] (partition-all 3 (string/split-lines input-str)))

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input-2)
                   (mapcat get-common-items)
                   (map get-priority)
                   (reduce +)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: The total priority of the incorrectly packed items is " (solution-2 input-str))))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 03:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
