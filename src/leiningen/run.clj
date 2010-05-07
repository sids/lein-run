(ns leiningen.run
  (:use [leiningen.compile :only (eval-in-project)])
  (:require clojure.main))

(defn- find-in-project
  "Returns the absolute path of a file given a path relative to the project root.
Returns nil if the file does not exist."
  [project path]
  (let [file (java.io.File. (:root project) path)]
    (when (.isFile file)
      (.getPath file))))

(defn- call-in-project
  "Loads the project namespaces as well as all its dependencies and then calls
ns/f, passing it the args."
  ([project ns & [f & args]]
     (if f
       (let [ns   (symbol ns)
             f    (symbol f)
             args (map str args)]
         (eval-in-project project
                          `(try (do (require '~ns)
                                    (@(ns-resolve '~ns '~f) ~@args))
                                (catch java.io.FileNotFoundException e#
                                  (println "ERROR: Could not find namespace" (str '~ns))))))
       (apply call-in-project project
              ns '-main args))))

(defn- run-in-project
  "Runs the file using clojure.main/main. Passes on the args as command line args."
  [project file & args]
  (apply clojure.main/main file args))

(defn run
  "Call a function in a new process or run a .clj file.

USAGE: lein run <namespace> [<function> [<arg> ...]]
Calls namespace/function in a new process; function defaults to -main.

USAGE: lein run <file> [<arg> ...]
Runs the .clj file using clojure.main.

USAGE: lein run <alias> [<arg> ...]
Aliases can be defined in project.clj as
    :run-aliases {:alias1 [a-namespace a-function \"arg1\" \"arg2\"]
                  :alias2 [\"a-file\" \"arg1\" \"arg2\"]}
Args from the command line are appended to args from the alias definition."
  [project & [first-arg & rest-args]]
  (if first-arg
    (if-let [args (get (:run-aliases project) first-arg
                       (get (:run-aliases project) (keyword first-arg)))]
      (apply run project
             (concat (map str args) rest-args))
      (if-let [file (find-in-project project first-arg)]
        (apply run-in-project project
               file rest-args)
        (apply call-in-project project
               first-arg rest-args)))
    (println "What shall I run? `lein help run`:\n\n"
             (:doc (meta (var run))))))
