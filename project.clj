(defproject lein-run "1.0.1-SNAPSHOT"
  :description "A leiningen plugin/task to call a function in a new process or 'run' a .clj file."
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]]
  :dev-dependencies [[leiningen "1.3.1"]
		     [lein-clojars "0.6.0"]]
  :disable-implicit-clean true
  :run-aliases {:default [leiningen.run.test1 hello]
                :test-1  [leiningen.run.test1 hello]
                :test-2  [leiningen.run.test1]
                "test-3" [leiningen.run.test1 -main "sweet"]
                :test-4  ["src/leiningen/run/test2.clj" "world"]})
