(ns aoc-2022-clojure.day08-test
  (:require [aoc-2022-clojure.day08 :as sut]
            [clojure.test :as t]))

(def test-input "30373
25512
65332
33549
35390")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 21 (sut/solution-1 test-input))))
  (t/testing "Part 1 real input"
    (t/is (= 1672 (sut/solution-1 sut/input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 8 (sut/solution-2 test-input))))
  (t/testing "Part 2 real input"
    (t/is (= 327180 (sut/solution-2 sut/input)))))
