(ns clojure-api-starter.core
  (:use org.httpkit.server)
  (:require [compojure.handler :refer [api]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [clojure-api-starter.routes :refer [routes]]
            [ring.middleware.reload :refer [wrap-reload]]
            [clojure-api-starter.service.db :refer [db-conn db-root-namespace]])
  (:gen-class))

(def app
  (-> (api routes)
      wrap-json-params
      wrap-json-response))

(defn -main []
  (db-conn)
  (db-root-namespace)
  (run-server (wrap-reload #'app) {:port 3000})
  (println "clojure-api-starter"))