(ns aoc-2022-clojure.day05
  (:require [clojure.string :as string]))

;; Part 1

(defn parse-initial-state
  "Parse the initial state into a vector of lists representing the contents. Heads are the top item."
  [input-str]
  (let [str-lines (string/split-lines input-str)
        longest-line-length (reduce max (map count str-lines))
        str-lines-padded (map #(take longest-line-length (concat % (repeat \space))) str-lines)
        container-strs (map (partial partition-all 3 4) str-lines-padded)
        container-trimmed-strs (map (partial map (partial second)) container-strs)
        transposed (apply (partial mapv list) container-trimmed-strs)
        transposed-filtered (map (partial filter #(java.lang.Character/isLetter %)) transposed)]
    (into (vector) transposed-filtered)))

(defn parse-operations
  "Parse the operations into a vector of vectors in the form [number from to]"
  [input-str]
  (map (comp (partial apply vector) (partial map parse-long) (partial drop 1))
       (re-seq #"move (\d+) from (\d+) to (\d+)" input-str)))

(defn read-input-1
  "Read the input and split it into a list of pairs."
  [input-str]
  (let [[initial-state-str operations-str] (string/split input-str #"\n\n")
        initial-state (parse-initial-state initial-state-str)
        operations (parse-operations operations-str)]
    [initial-state operations]))

(defn apply-operation
  "Apply an operation to a state.  Reversese the order of the transferred items if reverse-transferred is true."
  [reverse-transferred]
  (fn
    [state [cnt from to]]
    (let [from-idx (- from 1)
          to-idx (- to 1)
          from-stack (state from-idx)
          [taken from-stack-new] (split-at cnt from-stack)
          to-stack (state to-idx)
          to-stack-new (concat (if reverse-transferred (reverse taken) taken) to-stack)]
      (assoc state from-idx from-stack-new to-idx to-stack-new))))

(defn get-topmost-items
  "Get the topmost items as a sequence."
  [state] (map first state))

(defn solution-1
  "Solve the first part"
  [input-str]
  (let [[state operation] (read-input-1 input-str)
        final-state (reduce (apply-operation true) state operation)]
    (string/join (get-topmost-items final-state))))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The topmost containers are " (solution-1 input-str))))

;; Part 2

(defn solution-2
  "Solve the second part"
  [input-str]
  (let [[state operation] (read-input-1 input-str)
        final-state (reduce (apply-operation false) state operation)]
    (string/join (get-topmost-items final-state))))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: The topmost containers are " (solution-2 input-str))))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 05:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
