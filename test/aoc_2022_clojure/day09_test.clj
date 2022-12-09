(ns aoc-2022-clojure.day09-test
  (:require [aoc-2022-clojure.day09 :as sut]
            [clojure.test :as t]))

(def test-input-1 "R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2")

(def test-input-2 "R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 13 (sut/solution-1 test-input-1))))
  (t/testing "Part 1 actual input"
    (t/is (= 6098 (sut/solution-1 sut/input)))))

(t/deftest part-2
  (t/testing "Part 2 test input 1"
    (t/is (= 1 (sut/solution-2 test-input-1))))
  (t/testing "Part 2 test input 2"
    (t/is (= 36 (sut/solution-2 test-input-2))))
  (t/testing "Part 2 actual input"
    (t/is (= 2597 (sut/solution-2 sut/input)))))
