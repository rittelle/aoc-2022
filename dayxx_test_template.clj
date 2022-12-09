(ns aoc-2022-clojure.dayxx-test
  (:require [aoc-2022-clojure.dayxx :as sut]
            [clojure.test :as t]))

(def test-input "???")

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 42 (sut/solution-1 test-input))))
  (t/testing "Part 1 actual input"
    (t/is (= 42 (sut/solution-1 sut/input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 42 (sut/solution-2 test-input))))
  (t/testing "Part 2 actual input"
    (t/is (= 42 (sut/solution-2 sut/input)))))
