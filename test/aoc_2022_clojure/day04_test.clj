(ns aoc-2022-clojure.day04-test
  (:require [aoc-2022-clojure.day04 :as sut]
            [clojure.test :as t]))

(def test-input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 2 (sut/solution-1 test-input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 4 (sut/solution-2 test-input)))))
