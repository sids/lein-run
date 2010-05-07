(ns leiningen.run-example
  (:use [leiningen.compile :only (eval-in-project)]))

(defn run-example
  "Runs an example from nerchuko.examples.*.

USAGE: lein run-example <example-name> [<arg1> <arg2>]

Running an example without any additional args usually prints out a short help.

Possible examples-names are:
newsgroups
spambase"
  [project & [name & args]]
  (if name
    (eval-in-project project
                     `(let [ns# (symbol (str "nerchuko.examples." ~name ".main"))]
                        (require ns#)
                        (@(ns-resolve ns# '~'-main) ~@args)
                        nil))
    (println (:doc (meta (var run-example))))))
