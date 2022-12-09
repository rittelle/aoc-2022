(ns aoc-2022-clojure.day09-test
  (:require [aoc-2022-clojure.day09 :as sut]
            [clojure.test :as t]))

(def test-input "R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 42 (sut/solution-1 test-input))))
  (t/testing "Part 1 actual input"
    (t/is (= 42 (sut/solution-1 test-input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 42 (sut/solution-2 test-input))))
  (t/testing "Part 2 actual input"
    (t/is (= 42 (sut/solution-2 test-input)))))
