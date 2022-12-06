(ns aoc-2022-clojure.day06)

;; Part 1

(defn read-input-1
  "Read the input and split it into a list of pairs."
  [input-str] 42)

(defn get-start-marker-end-idx
  "Return the first index where the element and its n-1 predecessors are all pairwise inequal."
  [n input]
  (->> input
       ;; generate a sequence of sequences that are shifted, so that the respective
       ;; elements at position n are the last 4 elements in the input
       (partition n 1)
       ;; transform the sequence of sequences into a series of bools that are true
       ;; if all elements are not equal to each other
       (map (comp (partial = n) count set))
       ;; take until the first set of items are distinct
       (take-while false?)
       ;; calculate the offset
       (count)
       ;;  compensate for the items skipped that had no n-1 predecessors and 1-based indexing
       (+ n)))

(defn solution-1
  "Solve the first part"
  [input] (get-start-marker-end-idx 4 input))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The first index where the start sequence is finished is " (solution-1 input-str))))

;; Part 2

(defn solution-2
  "Solve the second part"
  [input] (get-start-marker-end-idx 14 input))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: The first index where the start sequence is finished is " (solution-2 input-str))))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 06:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
