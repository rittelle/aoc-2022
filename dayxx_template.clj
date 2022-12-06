(ns aoc-2022-clojure.day0x)

;; Part 1

(defn read-input-1
  "Read the input and split it into a list of pairs."
  [input-str] 42)

;; …

(defn solution-1
  "Solve the first part"
  [input-str] (->> input-str
                   (read-input-1)
                   ;; …
                   (reduce +)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: ??? " (solution-1 input-str))))

;; Part 2

(defn read-input-2
  "Read the input and split it into a list of lists representing the groups."
  [input-str] 42)

;; …

(defn solution-2
  "Solve the second part"
  [input-str] (->> input-str
                   (read-input-2)
                   ;; …
                   (reduce +)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: ??? " (solution-2 input-str))))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 0x:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
