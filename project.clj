(defproject lein-run "0.1.0-SNAPSHOT"
  :description "A leiningen plugin to call a function in a new process or run a .clj file."
  :namespaces [leiningen.run]
  :dependencies [[org.clojure/clojure "1.1.0"]
                 [org.clojure/clojure-contrib "1.1.0"]]
  :dev-dependencies [[leiningen/lein-swank "1.1.0"]
                     [lein-clojars "0.5.0-SNAPSHOT"]]
  :run-aliases {:test-1  [leiningen.run.test1 hello]
                :test-2  [leiningen.run.test1]
                "test-3" [leiningen.run.test1 -main "sweet"]
                :test-4  ["src/leiningen/run/test2.clj" "world"]})
