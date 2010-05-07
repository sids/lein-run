lein-run Leiningen Plugin
=========================

A leiningen plugin to call a function in a new process or run a .clj file.

lein-run is extremely useful when you want to launch long-running
Clojure process from the command line. For example, it can be used to
start a server (a web server like Compojure) or to start a process
that will run in an infinite loop (a process waiting for a messages
from a message queue, a twitter client etc.)

Setup
-----

lein-run is available from [clojars](http://clojars.org/lein-run).
Just add it as a dependency in your project.clj and you're good to
go. Refer the [clojars page](http://clojars.org/lein-run) for the
latest version to use.

    :dependencies [[lein-run "0.1.0-SNAPSHOT"]]

Usage
-----

    lein run <file> [<arg> ...]

file must be a path relative to the project root. It will be run
using clojure.main/main. Args will be passed on as *command-line-args*.

    lein run <namespace> [<function> [<arg> ...]]

If function is not given, it defaults to -main. Calls
namespace/function passing it all the args.

    lein run <alias> [<arg> ...]

Aliases for running a file or calling a function can be defined in
project.clj and referred to here. Args from the command line are
appended to args from the alias definition.

    :run-aliases {:alias1 [a-namespace a-function "arg1" "arg2"]
                  :alias2 ["a-file" "arg1" "arg2"]}

Alias names can be keywords or strings. All args (including namespace
name, function name and file path) will be converted to strings.

Example
-------

Let's say you have a simple Compojure webapp:

    (ns hello-www.core
      (:use compojure.core
            ring.adapter.jetty)
      (:require [compojure.route :as route]))

    (defroutes example
      (GET "/" [] "<h1>Hello World Wide Web!</h1>")
      (route/not-found "Page not found"))

    (defn start-server [port]
      (run-jetty example {:port (Integer/parseInt port)}))

You can start the server this way:

    lein run hello-www.core start-server 8080

You can also define an alias:

    :run-aliases {:server [hello-www.core start-server]}

and then use it to start the server:

    lein run server 8080

If you hardcode the port into the alias definition:

    :run-aliases {:server [hello-www.core start-server 8080]}

you won't have to specify it on the command line:

    lein run server

License
-------

Copyright (C) [Siddhartha Reddy](http://www.siddhartha-reddy.com/).

Thanks to Phil Hagelberg, Alex Osborne and Dan Larkin for the terrific
build tool that is leiningen.

Distributed under the Eclipse Public License, the same as Clojure uses. See the file COPYING.
