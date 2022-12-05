(ns aoc-2022-clojure.day05-test
  (:require [aoc-2022-clojure.day05 :as sut]
            [clojure.test :as t]))

(def test-input "    [D]
[N] [C]
[Z] [M] [P]
 1   2   3

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= "CMZ" (sut/solution-1 test-input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= "MCD" (sut/solution-2 test-input)))))
