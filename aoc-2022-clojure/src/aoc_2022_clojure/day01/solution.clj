(ns aoc-2022-clojure.day01.solution
  "day 01 solution"
  (:require [clojure.string :as string])
  (:import [java.lang Integer]))

;; Part 1

(defn read-input
  "Splits an input string into a sequence where each element contains an index and a vector
  corresponding to an elf."
  [input-str]
  (let [read-elf-calories (fn [s] (map #(Integer/parseUnsignedInt %) (string/split s #"\n")))
        read-elf (fn [idx s] (vector (+ idx 1) (read-elf-calories s)))
        read-elves (fn [s] (map-indexed read-elf (string/split s #"\n\n")))]
    (read-elves input-str)))

(defn get-elf-calories
  "Return the number of calories carried by a given elf."
  [elf] (reduce + (second elf)))

(defn find-elf-with-max-calories
  "Finds and returns the elf with the maximum calorie amount."
  [data] (reduce #(max-key get-elf-calories %1 %2) data))

(defn solution-1
  "Solve the first part with the given input."
  [input-str] (get-elf-calories (find-elf-with-max-calories (read-input input-str))))

(defn print-solution-1
  "Print a message containing the solution to the first part to stdout."
  [input-str] (println (str "  Part A: An elf carries " (solution-1 input-str) " calories which is the most.")))

;; Part 2

(defn remove-elf
  "Remove elf with idx from the collection."
  [elf-idx collection]
  (remove #(= elf-idx (first %)) collection))

;; The more efficient, but longer solution
(defn get-top-n-elves
  "Find the n elves that carry the most calories."
  [elves n]
  (second (reduce (fn [[remaining-elves top] _]
                    (let [elf (find-elf-with-max-calories remaining-elves)
                          elf-idx (first elf)
                          without-elf (remove-elf elf-idx remaining-elves)]
                      [without-elf (conj top elf)]))
                  [elves []]
                  (range n))))

;; The shorter, but less efficient version
(defn get-top-n-elves
  "Find the n elves that carry the most calories."
  [elves n]
  (let [sorted-elves (reverse (sort-by get-elf-calories elves))]
    (take n sorted-elves)))

(defn solution-2
  "Solve the second part with the given input."
  [input-str] (reduce + (map get-elf-calories (get-top-n-elves (read-input input-str) 3))))

(defn print-solution-2
  "Print a message containing the solution to the second part to stdout."
  [input-str] (println (str "  Part B: Three elves carry " (solution-2 input-str) " calories together.")))

(defn print-solution
  "Print a message containing the solution to the complete exercise to stdout."
  [input-str]
  (println "Day 01:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
