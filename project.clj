(defproject kibu/component-cljx "0.2.3-SNAPSHOT"
  :description "Managed lifecycle of stateful objects"
  :url "https://github.com/kibu-australia/component-cljx"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :min-lein-version "2.1.3"  ; added :global-vars
  :dependencies [[kibu/dependency-cljx "0.1.2-SNAPSHOT"]]
  :global-vars {*warn-on-reflection* true}

  :aliases {"deploy" ["do" "clean," "cljx" "once," "deploy" "clojars"]
            "test" ["do" "clean," "cljx" "once," "test," "with-profile" "dev" "cljsbuild" "test"]}

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.7.0-alpha3"]
                                  [org.clojure/clojurescript "0.0-2268"]
                                  [org.clojure/tools.namespace "0.2.4"]]}}

  :plugins [[com.keminglabs/cljx "0.4.0"]
            [lein-cljsbuild "1.0.3"]
            [com.cemerick/clojurescript.test "0.3.1"]]
  
  :cljx {:builds [{:source-paths ["src"]
                   :output-path "target/classes"
                   :rules :clj}
                  {:source-paths ["src"]
                   :output-path "target/classes"
                   :rules :cljs}
                  {:source-paths ["test"]
                   :output-path "target/test"
                   :rules :clj}
                  {:source-paths ["test"]
                   :output-path "target/test"
                   :rules :cljs}]}
  
  :hooks [cljx.hooks]
  :source-paths ["target/classes" "target/dev" "target/classes"]
  :test-paths ["target/test"]
  :cljsbuild {:builds {:dev {:source-paths ["src" "target/classes"]
                             :compiler {:output-dir "target/dev"
                                        :output-to "target/dev/component.js"
                                        :source-map "target/dev/component.js.map"
                                        :optimization :whitespace
                                        :pretty-print true}}
                       :test {:source-paths ["target/classes" "target/test" "src" "test"]
                              :compiler {:output-to "target/test/unit-test.js"          
                                         :optimizations :whitespace}}}
              :test-commands {"unit-tests" ["phantomjs" :runner
                                           "target/test/unit-test.js"]}})
