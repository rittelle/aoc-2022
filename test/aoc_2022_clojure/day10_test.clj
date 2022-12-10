(ns aoc-2022-clojure.day10-test
  (:require [aoc-2022-clojure.day10 :as sut]
            [clojure.test :as t]))

(def test-input-1 "noop
addx 3
addx -5")

(def test-input-2 "addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop")

(t/deftest part-1
  (t/testing "First example program"
    (t/is (= [1 1 1 4 4 -1]
             (->> test-input-1
                  (sut/read-input)
                  (sut/insert-pause-cycles)
                  (sut/execute-program-1)
                  (map :reg-x)))))
  (t/testing "Part 1 test input states"
    (let [program-result (->> test-input-2
                              (sut/read-input)
                              (sut/insert-pause-cycles)
                              (sut/execute-program-1))
          indexes (map dec [20 60 100 140 180 220])]
      (t/is (= [21 19 18 21 16 18] (->> program-result
                                        (sut/take-idxs indexes)
                                        (map :reg-x))))
      (t/is (= [420 1140 1800 2940 2880 3960] (->> program-result
                                                   (sut/calculate-signal-strengths)
                                                   (sut/take-idxs indexes))))))
  (t/testing "Part 1 test input"
    (t/is (= 13140 (sut/solution-1 test-input-2))))
  (t/testing "Part 1 actual input"
    (t/is (= 11780 (sut/solution-1 sut/input)))))

(def screen-output-test "##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....")

(def screen-output-actual "###..####.#..#.#....###...##..#..#..##..
#..#....#.#..#.#....#..#.#..#.#..#.#..#.
#..#...#..#..#.#....###..#..#.#..#.#..#.
###...#...#..#.#....#..#.####.#..#.####.
#....#....#..#.#....#..#.#..#.#..#.#..#.
#....####..##..####.###..#..#..##..#..#.")

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= screen-output-test (sut/solution-2 test-input-2))))
  (t/testing "Part 2 actual input"
    (t/is (= screen-output-actual (sut/solution-2 sut/input)))))
