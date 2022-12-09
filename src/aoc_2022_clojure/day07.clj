(ns aoc-2022-clojure.day07
  (:require [clojure.string :as string]
            [clojure.string :as str]))

;; Part 1

(defn read-path
  "Convert a path to the internal representation (a list)."
  [str]
  (filter not-empty (string/split str #"/")))

(defn simplify-path
  "Simpliifies a path by resolving occurrences of .. ."
  [path]
  (reverse (reduce (fn [result comp]
                     (case comp
                       "" result
                       ".." (pop result)
                       (conj result comp)))
                   (list)
                   path)))

(defn read-path-in-context
  "Return new-path-str as a resolved relative or absolute path."
  [current-path new-path-str]
  (if (string/starts-with? new-path-str "/")
    (read-path new-path-str)                          ; the path is absolute
    (simplify-path (concat current-path (read-path new-path-str)))))

(defn read-ls-output-line
  "Return a sequence of pairs [size path] from a ls output line."
  [current-path output-line]
  (let [[size file] (string/split output-line #" ")
        is-dir (= size "dir")
        int-size (parse-long size)
        full-path (read-path-in-context current-path file)]
    (if is-dir
      ["dir" full-path]
      [int-size full-path])))

(defn read-ls-output
  "Return a sequence of pairs [size path] from a ls output."
  [current-path output]
  (map (partial read-ls-output-line current-path) output))

(defn convert-file-list-to-map
  "Converts a list of files and directories into a tree structure based on maps."
  [file-list]
  (defn merge-entry-into-map [tree [size path]] (let []))
  (reduce (fn [tree entry] 42) {} file-list))

(defn add-entry-to-tree
  "Add an entry into a file tree structure."
  [tree [size path]]
  (let [first-part (first path)
        rest-part (rest path)
        is-dir (= size "dir")
        record (if is-dir {} size)]
    (assoc tree
           first-part
           (if (empty? rest-part)
             record
             (add-entry-to-tree (get tree
                                     first-part
                                     {})
                                [size rest-part]))) ) )

(defn read-input-1
  "Read the input and split it into a list of pairs."
  [input-str] (loop [lines-left (string/split-lines input-str)
                     current-path "/"
                     tree {}]
                (if (empty? lines-left)
                  tree
                  (let [current-line (first lines-left)
                        remaining-lines (rest lines-left)
                        words (string/split current-line #" +") ; first is always the $
                        command (second words)]
                    (case command
                      "ls" (let [[ls-output remaining-session] (split-with #(not (string/starts-with? % "$ ")) remaining-lines)
                                 new-file (read-ls-output current-path ls-output)]
                             (recur remaining-session
                                    current-path
                                    (reduce add-entry-to-tree tree new-file)))
                      "cd" (recur remaining-lines
                                  (read-path-in-context current-path (nth words 2))
                                  tree)
                      (assert false "Unknown command"))))))

(defn calculate-total-size
  "Calculate the total size of a tree or an entry."
  [tree]
  (if (number? tree)
    tree
    (transduce (map calculate-total-size) + (vals tree))))

(defn flatmap-dirs
  "Return a transduces that applies f to all directories and return a flattened sequence."
  [f]
  (fn  [rf]
    (fn ([] (rf))
      ([result] (rf result))
      ([result input] (if (number? (val input))
                        result
                        (reduce rf (rf result (f (val input)))
                                (transduce  (flatmap-dirs f) conj [] (val input))))))))

(defn solution-1
  "Solve the first part"
  [input-str] (transduce
               (comp (flatmap-dirs calculate-total-size)
                     (filter (partial > 100000)))
               +
               (read-input-1 input-str)))

(defn print-solution-1
  "Print the solution to part 1 to stdout."
  [input-str] (println (str "  Part 1: the sum of the sizes of all directories <=100000 is " (solution-1 input-str))))

;; Part 2

(defn solution-2
  "Solve the second part"
  [input-str] (let [total-space 70000000
                    required-space 30000000
                    tree (read-input-1 input-str)
                    free-space (- total-space (calculate-total-size tree))
                    needed-space (- required-space free-space)]
                ;; there will be no bigger size than total-space
                (transduce (comp (flatmap-dirs calculate-total-size)
                                 (filter (partial < needed-space))) min total-space tree)))

(defn print-solution-2
  "Print the solution to part 2 to stdout."
  [input-str] (println (str "  Part 2: the size of the smallest, yet large enough directory is " (solution-2 input-str))))

;; Both parts combined

(defn print-solution
  "Print the solution both parts to stdout."
  [input-str]
  (println "Day 0x:")
  (print-solution-1 input-str)
  (print-solution-2 input-str))
