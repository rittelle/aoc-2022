(ns aoc-2022-clojure.day10
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

;; Part 1

(defrecord MachineState [reg-x screen-output])

(defn make-init-machine-state
  "Creates an instance of MachineState with the default state."
  [] (MachineState. 1 ""))

(defn read-instruction
  "Read a single instruction from the input."
  [input-str]
  (let [[command-str & args] (string/split input-str #" +")
        command ({"noop" :noop, "addx" :addx} command-str)]
    (cons command args)))

(defn read-input
  "Read the input and split it into a list of instructions and arguments."
  [input-str] (map read-instruction (string/split-lines input-str)))

(defn insert-pause-cycles
  "Inserts noops before instructions that take multiple cycles to complete, so that each cycle
  corresponds to one element in the sequence."
  [instructions]
  (mapcat (fn [instruction] (case (first instruction)
                              :noop [instruction]
                              :addx [[:noop] instruction]))
          instructions))

(defn execute-instruction
  "Execute a single instruction and return the new machine state."
  [machine-state instruction]
  (case (first instruction)
    :noop machine-state
    :addx (let [arg (parse-long (second instruction))
                new-x (+ (:reg-x machine-state) arg)]
            (assoc machine-state :reg-x new-x))))

(defn execute-program-1
  "A transducer that runs a program and return a sequence of the machine states after each
  instruction. The first element is the initial state."
  [instructions]
  (reductions execute-instruction (make-init-machine-state) instructions))

(defn calculate-signal-strengths
  "Calculates the signal strengths for a sequence of machine states."
  [coll]
  (->> coll
       (map :reg-x)
       (map-indexed #(* (inc %1) %2))))

(defn take-idxs
  "Take the elements from a list of indexes from a sequence."
  [indexes coll]
  (let [idx-set (set indexes)]
    (for [[idx e] (map-indexed vector coll) :when (contains? idx-set idx)] e)))

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   (insert-pause-cycles)
                   (execute-program-1)
                   (calculate-signal-strengths)
                   (take-idxs (map dec [20 60 100 140 180 220]))
                   (reduce +)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The sum of the signal strengths is " (solution-1 input-str))))

;; Part 2

(def sprite-width 3)

(def sprite-offset-from-center (/ (dec sprite-width) 2))

(def screen-width 40)

(defn current-pixel-in-sprite?
  "Return true if the current pixel is contained in the sprite."
  [machine-state]
  (let [sprite-pos (:reg-x machine-state)
        pixel-pos (mod (count (:screen-output machine-state)) screen-width)]
    (<= (- sprite-pos sprite-offset-from-center)
        pixel-pos
        (+ sprite-pos sprite-offset-from-center))))

(defn insert-next-pixel
  "Inserts the next pixel onto the screen."
  [machine-state]
  (let [screen-output (:screen-output machine-state)
        pixel-in-sprite (current-pixel-in-sprite? machine-state)
        pixel (if pixel-in-sprite \# \.)]
    (assoc machine-state :screen-output (str screen-output pixel))))

(defn execute-program-2
  "Execute a program in the second part and updates the screen."
  [instructions]
  (reductions #(execute-instruction (insert-next-pixel %1) %2) (make-init-machine-state) instructions))

(defn get-screen-output
  "Get the screen output from a machine state."
  [machine-state]
  (string/join "\n" (map (partial reduce str) (partition screen-width (:screen-output machine-state)))))

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input)
                   (insert-pause-cycles)
                   (execute-program-2)
                   (last)
                   (get-screen-output)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str]
  (println "  Part 2: The screen output is: " )
  (println (solution-2 input-str)))

;; Both parts combined

(def input (slurp (io/resource "day10-input.txt")))

(defn print-solution
  "Print the solution both parts to stdout."
  []
  (println "Day 10:")
  (print-solution-1 input)
  (print-solution-2 input))
