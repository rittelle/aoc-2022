(ns aoc-2022-clojure.day03-test
  (:require [aoc-2022-clojure.day03 :as sut]
            [clojure.test :as t]))

(def test-input "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 157 (sut/solution-1 test-input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 70 (sut/solution-2 test-input)))))
