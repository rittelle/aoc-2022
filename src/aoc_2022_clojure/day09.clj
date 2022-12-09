(ns aoc-2022-clojure.day09
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

;; Part 1

(defn make-rope-position
  "Create a rope structure instance."
  [length] (repeat length [0 0]))

(defn read-input
  "Read the input and split it into a list of pairs of direction and step size."
  [input-str] (map (comp (fn [[dir amount]]
                           [(case dir
                              "L" :left
                              "R" :right
                              "D" :down
                              "U" :up)
                            (parse-long amount)])
                         #(string/split %  #" "))
                   (string/split-lines input-str)))

(defn flatten-movement
  "Return a sequence in which a [dir amount] is replaced with amount dirs."
  [instructions]
  (mapcat (fn [[dir amount]] (repeat amount dir)) instructions))

(defn move
  "Move the element into direction."
  [[x y] direction]
  (case direction
    :left [(dec x) y]
    :right [(inc x) y]
    :up [x (dec y)]
    :down [x (inc y)]
    (throw (ex-info "Invalid direction" {:direction direction}))))

(defn move-tail
  "Move the tail according to physics and return it."
  [head tail-element]
  (let [[head-x head-y] head
        [tail-x tail-y] tail-element
        ;; ±1 counts as touching
        is-touching (and (>= 1 (abs (- head-x tail-x)))
                         (>= 1 (abs (- head-y tail-y))))
        update-component (fn [tail head]
                           (cond
                             (< tail head) (inc tail)
                             (> tail head) (dec tail)
                             :else tail))
        new-tail-x (update-component tail-x head-x)
        new-tail-y (update-component tail-y head-y)]
    (if is-touching
      tail-element
      [new-tail-x new-tail-y])))

(defn apply-motion
  [rope direction]
  (let [[head & tail] rope
        new-head (move head direction)
        new-tail (rest (reductions move-tail new-head tail))]
    (cons new-head new-tail)))

(defn find-number-of-visited-fields
  "Finds the number of visited fields of the last element (the tail) after applying directions."
  [rope-length directions]
  (->> directions
       (flatten-movement)
       (reductions apply-motion (make-rope-position rope-length))
       (map last)
       (set)
       (count)))

(defn solution-1
  "Solve the first part"
  [input-str] (find-number-of-visited-fields 2 (read-input input-str)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The tail visits " (solution-1 input-str) " fields")))

;; Part 2

(defn solution-2
  "Solve the second part"
  [input-str] (find-number-of-visited-fields 10 (read-input input-str)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: The tail visits " (solution-2 input-str) " fields")))

;; Both parts combined

(def input (slurp (io/resource "day09-input.txt")))

(defn print-solution
  "Print the solution both parts to stdout."
  []
  (println "Day 09:")
  (print-solution-1 input)
  (print-solution-2 input))
