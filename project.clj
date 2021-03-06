(defproject biiwide/tools.cli "0.3.11-SNAPSHOT"

  :description "Command line arguments library."
  :parent [org.clojure/pom.contrib "0.1.2"]
  :url "https://github.com/biiwide/tools.cli"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]

  :dependencies [[org.clojure/clojure "1.6.0"]]

  :profiles {:1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :1.9 {:dependencies [[org.clojure/clojure "1.9.0"]]}

             ;; Local CLJS development; not in pom.xml
             :dev {:dependencies [[org.clojure/clojurescript "0.0-2080" :scope "test"]]
                   :plugins [[lein-cljsbuild "1.0.0"]
                             [com.birdseye-sw/lein-dalap "0.1.1"]
                             [com.cemerick/clojurescript.test "0.2.1" :scope "test"]]
                   :hooks [leiningen.dalap]
                   :cljsbuild {:builds [{:source-paths ["src/main/clojure/cljs"
                                                        "src/test/clojure/cljs"]
                                         :compiler {:output-to "target/cli_test.js"
                                                    :optimizations :whitespace
                                                    :pretty-print true}}]
                               :test-commands {"phantomjs" ["phantomjs"
                                                            :runner
                                                            "target/cli_test.js"]}}}}


  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version"
                   "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :aliases {"test-all" ["with-profile" "test,1.6:test,1.7:test,1.8:test,1.9" "test"]
            "check-all" ["with-profile" "1.6:1.7:1.8:1.9" "check"]}

  :min-lein-version "2.0.0")
