(defproject lein-run "1.0.0"
  :description "A leiningen plugin to call a function in a new process or run a .clj file."
  :dependencies [[org.clojure/clojure "1.1.0"]
                 [org.clojure/clojure-contrib "1.1.0"]]
  :dev-dependencies [[leiningen "1.1.0"]
		     [lein-clojars "0.6.0"]]
  :disable-implicit-clean true
  :run-aliases {:test-1  [leiningen.run.test1 hello]
                :test-2  [leiningen.run.test1]
                "test-3" [leiningen.run.test1 -main "sweet"]
                :test-4  ["src/leiningen/run/test2.clj" "world"]})
