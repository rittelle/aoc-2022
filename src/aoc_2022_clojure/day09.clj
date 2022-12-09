(ns aoc-2022-clojure.day09
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

;; Part 1

(defrecord RopePosition [head tail])

(defn make-rope-position
  "Create a RopePosition instance."
  [] (->RopePosition [0 0] [0 0]))

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

(defn direction->vec
  "Convert a direction into a pair containing the change in coordinates."
  [dir]
  (case dir
    :left [-1 0]
    :right [1 0]
    :up [0 1]
    :down [0 -1]
    (throw (ex-info "Invalid dir" {:dir dir}))))

(defn apply-position-change
  "Apply a change in coordinates to a position."
  [[x y] [dx dy]] [(+ x dx) (+ y dy)])

(defn move-head
  "Move the head into direction"
  [rope-position direction]
  (let [head (:head rope-position)
        tail (:tail rope-position)
        new-head (apply-position-change head (direction->vec direction))]
    (->RopePosition new-head tail)))

(defn move-tail
  "Move the tail according to physics."
  [rope-position]
  (let [head (:head rope-position)
        [head-x head-y] head
        [tail-x tail-y] (:tail rope-position)
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
      rope-position
      (->RopePosition head [new-tail-x new-tail-y]))))

(defn apply-motion
  [rope-position direction]
  (move-tail (move-head rope-position direction)))

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input)
                   (flatten-movement)
                   (reductions apply-motion (make-rope-position))
                   (map :tail)
                   (set)
                   (count)))

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

(def input (slurp (io/resource "day09-input.txt")))

(defn print-solution
  "Print the solution both parts to stdout."
  []
  (println "Day 09:")
  (print-solution-1 input)
  (print-solution-2 input))
