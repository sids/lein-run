(ns leiningen.test-run
  (:use [leiningen.run] :reload-all)
  (:use [clojure.test]
        [clojure.contrib.shell-out :only (sh)]))

(defn lein-run [args]
  (apply sh "lein" "run" args))

(deftest test-call-fn
  (are [expected-output args] (= expected-output
                                 (lein-run args))
       "hello (world)\n"                ["leiningen.run.test1" "hello" "world"]
       "hello (world oh)\n"             ["leiningen.run.test1"]
       "hello (world oh sweet world)\n" ["leiningen.run.test1" "-main" "sweet" "world"]))

(deftest test-run-file
  (are [expected-output args] (= expected-output
                                 (lein-run args))
       "hello (world)\n" ["src/leiningen/run/test2.clj" "world"]))

(deftest test-alias
  (are [expected-output args] (= expected-output
                                 (lein-run args))
       "hello (world)\n"                ["test-1" "world"]
       "hello (world oh)\n"             ["test-2"]
       "hello (world oh sweet world)\n" ["test-3" "world"]
       "hello (world)\n"                ["src/leiningen/run/test2.clj" "world"]))
