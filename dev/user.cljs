(ns user
  (:require [atomist.main]
            [atomist.leiningen]
            [cljs.core.async :refer [<! chan]]
            [cljs-node-io.core :refer [slurp spit]]
            [atomist.local-runner :refer [set-env call-event-handler fake-push fake-command-handler]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(enable-console-print!)
(set-env :prod-github-auth)
(set-env :staging)

(comment

  (-> (fake-push "AEIB5886C" "slimslender" "clj3" "master")
      (assoc :configurations [{:parameters [{:name "policy" :value "manualConfiguration"}
                                            {:name "dependencies" :value "[[metosin/compojure-api \"1.1.13\"]]"}]}])
      (call-event-handler atomist.main/handler))

  (-> (fake-command-handler "AEIB5886C" "ShowLeiningenDependencies" "not used" "C013S77KK6K" "U1RCET8SV")
      (assoc :configurations [])
      (call-event-handler atomist.main/handler))

  (-> (fake-command-handler "AK748NQC5" "ShowLeiningenDependencies" "not used" "CDU23TC1H" "UDF0NFB5M")
      (assoc :parameters [{:name "policy" :value "manualConfiguration"}
                          {:name "dependencies" :value "[[org.clojure/clojure \"1.10.2\"]]"}])
      (assoc :configurations [{:parameters [{:name "policy" :value "manualConfiguration"}
                                            {:name "dependencies" :value "[[org.clojure/clojure \"1.10.2\"]]"}]}
                              {:parameters [{:name "policy" :value "latestSemVerAvailable"}
                                            {:name "dependencies" :value "[mount]"}]}
                              {:parameters [{:name "policy" :value "latestSemVerUsed"}
                                            {:name "dependencies" :value "[com.atomist/common]"}]}])
      (call-event-handler atomist.main/handler))

  (-> (fake-push "AK748NQC5" "atomisthqa" "clj1" "master")
      (assoc :parameters [])
      (assoc :configurations [{:parameters [{:name "policy" :value "manualConfiguration"}
                                            {:name "dependencies" :value "[[org.clojure/clojure \"1.10.2\"]]"}]}
                              {:parameters [{:name "policy" :value "latestSemVerAvailable"}
                                            {:name "dependencies" :value "[\"mount\"]"}]}
                              {:parameters [{:name "policy" :value "latestSemVerUsed"}
                                            {:name "dependencies" :value "[\"com.atomist/common\"]"}]}])
      (call-event-handler atomist.main/handler)))
