(ns aoc-2022-clojure.day0x)

;; Part 1

(defn read-input
  "Read the input and split it into a list of pairs."
  [input-str] (to-array-2d (map (partial map #(Character/digit % 10)) (string/split-lines input-str))))

;; …

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   ;; ...
                   ((constantly 42))))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: ??? " (solution-1 input-str))))

;; Part 2

;; …

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input)
                   ;; ...
                   ((constantly 42))))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: ??? " (solution-2 input-str))))

;; Both parts combined

(def input (slurp (io/resource "day0x-input.txt")))

(defn print-solution
  "Print the solution both parts to stdout."
  []
  (println "Day 0x:")
  (print-solution-1 input)
  (print-solution-2 input))
