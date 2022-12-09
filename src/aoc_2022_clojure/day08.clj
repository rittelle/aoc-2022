(ns aoc-2022-clojure.day08
  "day 08 solution"
  (:require [clojure.java.io :as io]
            [clojure.set :as set]
            [clojure.string :as string])
  (:import [java.lang Character]))

;; Part 1

(defn read-input
  "Read the input and split it into a list of pairs."
  [input-str] (to-array-2d (map (partial map #(Character/digit % 10)) (string/split-lines input-str))))

(defn transpose
  "Transposes the given grid"
  [grid]
  (apply (partial map vector) grid))

(defn apply-over-all-axes
  "Applies a function that alters a row/column over all rows and columns from both directions and joins the results."
  [axesf joinf grid]
  (let [apply-to-axes (partial map axesf)
        apply-to-axes-reversed #(map reverse (apply-to-axes (map reverse %)))
        rows grid
        columns (transpose grid)]
    ;; join the results for each element using joinf
    (map (partial map joinf)
         (apply-to-axes rows)
         (apply-to-axes-reversed rows)
         (transpose (apply-to-axes columns))
         (transpose (apply-to-axes-reversed columns)))))

(defn check-visibility
  "Checks for each tree whether it is visible from the outside from the start of the given axis."
  [axis]
  (reverse (first (reduce (fn [[result, tallest-height] height]
                            [(conj result (> height tallest-height))
                             (max tallest-height height)])
                          ['() -1]
                          axis))))

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   (apply-over-all-axes check-visibility #(or %1 %2 %3 %4))
                   (apply concat)
                   (filter true?)
                   (count)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: " (solution-1 input-str) " trees are visible from the outside.")))

;; Part 2

(defn calculate-viewing-distances
  "Calculate all viewing distances over an axis in the forward direction."
  [axis]
  (reverse (first (reduce (fn [[result, past-heights] height]
                            (let [[heights-visible rest] (split-with (partial > height) past-heights)
                                  heights-visible-count (count heights-visible)
                                  ;; if the vision is blocked by a tree, we have to add the blocking tree
                                  visible-trees (if (empty? rest) heights-visible-count (inc heights-visible-count))]
                              [(conj result visible-trees)
                               (conj past-heights height)]))
                          ['() '()]
                          axis))))

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input)
                   (apply-over-all-axes calculate-viewing-distances *)
                   (apply concat)
                   (reduce max 0)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: The highest scenic score is " (solution-2 input-str))))

;; Both parts combined

(def input (slurp (io/resource "day08-input.txt")))

(defn print-solution
  "Print the solution both parts to stdout."
  []
  (println "Day 08:")
  (print-solution-1 input)
  (print-solution-2 input))
