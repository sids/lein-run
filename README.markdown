A leiningen plugin to call a function in a new process or run a .clj file.

lein-run is extremely useful when you want to launch long-running
Clojure process from the command line. For example, it can be used to
start a server (a web server like Compojure) or to start a process
that will run in an infinite loop (a process waiting for a messages
from a message queue, a twitter client etc.)

## Installation

lein-run is available from [clojars](http://clojars.org/lein-run).
Just add it as a dependency in your project.clj and you're good to
go. Refer the [clojars page](http://clojars.org/lein-run) for the
latest version to use.

## Usage

    lein run <file> [<arg> ...]

<file> must be a path relative to the project root. It will be run
using clojure.main/main. Args will be passed on as *command-line-args*.

    lein run <namespace> [<function> [<arg> ...]]

If <function> is not given, it defaults to -main. Calls
namespace/function passing it all the args.

    lein run <alias> [<arg> ...]

Aliases for running a file or calling a function can be defined in
project.clj and referred to here. Args from the command line are
appended to args from the alias definition.

    :run-aliases {:alias1 [a-namespace a-function "arg1" "arg2"]
                  :alias2 ["a-file" "arg1" "arg2"]}

Alias names can be keywords or strings. All args (including namespace
name, function name and file path) will be converted to strings.

## License

Copyright (C) [Siddhartha Reddy](http://www.siddhartha-reddy.com/).

Thanks to Phil Hagelberg, Alex Osborne and Dan Larkin for the terrific
build tool that is leiningen.

Distributed under the Eclipse Public License, the same as Clojure uses. See the file COPYING.
