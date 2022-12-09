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

(defn get-inner-idxs
  "Returns a sequence of the indexes of the trees not at the border."
  [grid]
  (let [height (alength grid)
        width (alength (aget grid 0))]
    (for [y (range 1 (dec height))
          x (range 1 (dec width))]
      [y x]))
  )

(defn calculate-directions-where-visible
  "Calculates the directions from where the tree at the given coordinates is visible."
  [grid [tree-y tree-x]]
  (let [height (alength grid)
        width (alength (aget grid tree-y))
        tree-height (aget grid tree-y tree-x)
        trees-left (for [x (range tree-x)] (aget grid tree-y x))
        trees-right (for [x (range (inc tree-x) width)] (aget grid tree-y x))
        trees-above (for [y (range tree-y)] (aget grid y tree-x))
        trees-below (for [y (range (inc tree-y) height)] (aget grid y tree-x))
        visible-over? (fn [trees-in-line] (nil? (some (partial <= tree-height) trees-in-line)))]
    (set/union (if (visible-over? trees-left) #{:left} #{})
               (if (visible-over? trees-right) #{:right} #{})
               (if (visible-over? trees-above) #{:top} #{})
               (if (visible-over? trees-below) #{:bottom} #{}))))

(defn tree-visible-from-any-direction?
  "Returns true if the tree is visible from any direction."
  [grid tree-pos]
  (not-empty (calculate-directions-where-visible grid tree-pos)))

(defn count-visible-trees
  "Counts the visible trees from a grid."
  [grid]
  (let [height (alength grid)
        width (alength (aget grid 0))
        count-at-border (* 2 (+ height width -2))
        visible-inner (->> grid
                           (get-inner-idxs)
                           (filter (partial tree-visible-from-any-direction? grid))
                           (count))]
    (+ count-at-border visible-inner)))

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   (count-visible-trees)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: " (solution-1 input-str) " trees are visible from the outside.")))

;; Part 2

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
    (map (partial map joinf)
         (apply-to-axes rows)
         (apply-to-axes-reversed rows)
         (transpose (apply-to-axes columns))
         (transpose (apply-to-axes-reversed columns)))))

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
