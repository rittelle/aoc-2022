(ns aoc-2022-clojure.day02
  (:require [clojure.string :as string]
            [clojure.set :refer [map-invert]]))

;; Part 1

(def order [:rock :paper :scissors])

(def order-idx-diff-outcome-mapping {0 :draw    ; both picked the same
                                     1 :win     ; I picked the next element to the opponent
                                     2 :loss})  ; I picked the element before that of the oppenent

(def selection-score-mapping {:rock 1
                              :paper 2
                              :scissors 3})

(def outcome-score-mapping {:win 6
                            :draw 3
                            :loss 0})

(def opponent-mapping {"A" :rock
                       "B" :paper
                       "C" :scissors})

(def my-mapping {"X" :rock
                 "Y" :paper
                 "Z" :scissors})

(defn read-input
  "Parse the input from a string. Return a vector of pairs."
  [input-str] (map #(string/split % #" +") (string/split input-str #"\n")))

(defn decode-1
  "Decode the input from the first part."
  [encoded-strategy]
  (defn decode-game [[opp my]] [(get opponent-mapping opp) (get my-mapping my)] )
  (map decode-game encoded-strategy))

(defn get-outcome
  "Return the result of a single game."
  [[opp my]]
  (let [opp-idx (.indexOf order opp)
        my-idx (.indexOf order my)
        idx-diff (mod (- my-idx opp-idx) (count order)) ]
    (order-idx-diff-outcome-mapping idx-diff)))

(defn game-score
  "Returns the score of a game."
  [[opp my]]
  (let [outcome (get-outcome [opp my])
        outcome-score (outcome-score-mapping outcome)
        my-score (selection-score-mapping my)]
    (+ my-score outcome-score)))

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   (decode-1)
                   (map game-score)
                   (reduce +)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: The score would be " (solution-1 input-str) " points")))

;; Part 2

(def outcome-mapping {"X" :loss
                      "Y" :draw
                      "Z" :win})

(def outcome-order-idx-diff-mapping (map-invert order-idx-diff-outcome-mapping))

(defn decode-2
  "Decode the input from the second part."
  [encoded-strategy]
  (defn decode-game [[opp my]] [(get opponent-mapping opp) (get outcome-mapping my)])
  (map decode-game encoded-strategy))

(defn reconstruct-game
  "Reconstruct the selection of both players from the opponent's selection and the outcome."
  [[opp outcome]]
  (let [opp-idx (.indexOf order opp)
        idx-diff (outcome-order-idx-diff-mapping outcome)
        my-idx (mod (+ opp-idx idx-diff) 3)
        my (order my-idx)]
    [opp my]))

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input)
                   (decode-2)
                   (map reconstruct-game)
                   (map game-score)
                   (reduce +)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: The score would be " (solution-2 input-str) " points")))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 02:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
