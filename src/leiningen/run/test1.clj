(ns leiningen.run.test1)

(defn hello [& args]
  (println "hello" args))

(defn -main [ & args]
  (apply hello "world" "oh" args))
