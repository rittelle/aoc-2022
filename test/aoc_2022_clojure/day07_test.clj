(ns aoc-2022-clojure.day07-test
  (:require [aoc-2022-clojure.day07 :as sut]
            [clojure.test :as t]))

(def test-input "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

(t/deftest part-1
  (t/testing "Part 1 test read function"
    (let [tree (sut/read-input-1 test-input)]
      (t/is (= 584 (sut/calculate-total-size ((tree "a") "e"))))
      (t/is (= 94853 (sut/calculate-total-size (tree "a"))))
      (t/is (= 24933642 (sut/calculate-total-size (tree "d"))))
      (t/is (= 48381165 (sut/calculate-total-size tree)))))
  (t/testing "Part 1 test input"
    (t/is (= 95437 (sut/solution-1 test-input)))))

(t/deftest part-2
  (t/testing "Part 2 test input"
    (t/is (= 24933642 (sut/solution-2 test-input)))))
