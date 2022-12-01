(ns aoc-2022-clojure.day01.solution-test
  (:require [aoc-2022-clojure.day01.solution :as sut]
            [clojure.test :as t]))

(def test-input "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(t/deftest part-1-test-input
  (t/testing "Part 1 test input"
    (t/is (= (sut/solution-1 test-input) 24000))))

(t/deftest part-2-test-input
  (t/testing "Part 1 test input"
    (t/is (= (sut/solution-2 test-input) 45000))))
