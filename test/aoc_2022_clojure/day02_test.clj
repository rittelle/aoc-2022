(ns aoc-2022-clojure.day02-test
  (:require
   [aoc-2022-clojure.day02 :as sut]
   [clojure.test :as t]))

(def test-input "A Y\nB X\nC Z")

(t/deftest part-1
  (t/testing "game-score works"
    (t/is (= (sut/game-score [:paper :paper]) 5))
    (t/is (= (sut/game-score [:rock :scissors]) 3))
    (t/is (= (sut/game-score [:scissors :rock]) 7)))
  (t/testing "Part 1 test input"
    (t/is (= (sut/solution-1 test-input) 15))))

(t/deftest part-2
  (t/testing "reconstruct-game works"
    (t/is (= (sut/reconstruct-game [:paper :loss]) [:paper :rock])))
  (t/testing "Part 2 test input"
    (t/is (= (sut/solution-2 test-input) 12))))
