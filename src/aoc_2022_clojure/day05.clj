(ns aoc-2022-clojure.day05
  (:require [clojure.string :as string]))

;; Part 1

(defn letter?
  "Return c if it is a letter and nil otherwise."
  [ch] (if (java.lang.Character/isLetter ch) ch nil))

(defn
  conj-if
  "Perform conj if x is not nil, otherwise return coll unchanged."
  ([coll x] (if (some? x) (conj coll x) coll))
  ([coll] coll))

(defn parse-initial-state
  "Parse the initial state into a vector of lists representing the contents. Heads are the top item."
  [input-str]
  (let [str-lines (string/split-lines input-str)
        longest-line-length (transduce (map count) max 0 str-lines)
        str-lines-padded (map #(take longest-line-length (concat % (repeat \space))) str-lines)]
    (vec (transduce
          (map (comp (partial map letter?)
                     (partial take-nth 4)  ; extract the characters only
                     (partial drop 1)))    ; ^
          (partial map conj-if)            ; append each item to one of the sequences from init
          (repeat '())
          (reverse str-lines-padded)))))

(defn parse-operations
  "Parse the operations into a vector of vectors in the form [number from to]"
  [input-str]
  (map (partial into [] (comp (drop 1) (map parse-long)))
       (re-seq #"move (\d+) from (\d+) to (\d+)" input-str)))

(defn read-input-1
  "Read the input and split it into a list of pairs."
  [input-str]
  (let [[initial-state-str operations-str] (string/split input-str #"\n\n")
        initial-state (parse-initial-state initial-state-str)
        operations (parse-operations operations-str)]
    [initial-state operations]))

(defn apply-operation
  "Apply an operation to a state.  Applies transformer to the list of containers being transferred."
  [transformer]
  (fn
    [state [cnt from to]]
    (let [from-idx (- from 1)
          to-idx (- to 1)
          [taken from-stack-new] (split-at cnt (state from-idx))
          to-stack-new (concat (transformer taken) (state to-idx))]
      (assoc state
             from-idx from-stack-new
             to-idx to-stack-new))))

(defn get-topmost-items
  "Get the topmost items as a sequence."
  [state] (map first state))

(defn solution-1
  "Solve the first part"
  [input-str]
  (->> input-str
       (read-input-1)
       (apply reduce (apply-operation reverse))
       (get-topmost-items)
       (string/join)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The topmost containers are " (solution-1 input-str))))

;; Part 2

(defn solution-2
  "Solve the second part"
  [input-str]
  (->> input-str
       (read-input-1)
       (apply reduce (apply-operation identity))
       (get-topmost-items)
       (string/join)))

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
