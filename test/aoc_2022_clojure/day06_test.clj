(ns aoc-2022-clojure.day06-test
  (:require [aoc-2022-clojure.day06 :as sut]
            [clojure.test :as t]))

(def test-inputs-1 ["bvwbjplbgvbhsrlpgdmjqwftvncz"
                  "nppdvjthqldpwncqszvftbrmjlhg"
                  "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
                  "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"])

(def test-inputs-2 ["mjqjpqmgbljsphdztnvjfqwrcgsmlb"
                    "bvwbjplbgvbhsrlpgdmjqwftvncz"
                    "nppdvjthqldpwncqszvftbrmjlhg"
                    "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
                    "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"])

(t/deftest part-1
  (t/testing "Part 1 test input"
    (t/is (= 5 (sut/solution-1 (test-inputs-1 0))))
    (t/is (= 6 (sut/solution-1 (test-inputs-1 1))))
    (t/is (= 10 (sut/solution-1 (test-inputs-1 2))))
    (t/is (= 11 (sut/solution-1 (test-inputs-1 3))))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 19 (sut/solution-2 (test-inputs-2 0))))
    (t/is (= 23 (sut/solution-2 (test-inputs-2 1))))
    (t/is (= 23 (sut/solution-2 (test-inputs-2 2))))
    (t/is (= 29 (sut/solution-2 (test-inputs-2 3))))
    (t/is (= 26 (sut/solution-2 (test-inputs-2 4))))))
