(ns project.base
  (:require [clojure.java.jdbc :as jdbc]))

(defn create-db-spec []
  {:dbtype "mssql"
   :host (System/getenv "DB_HOST")
   :port (System/getenv "DB_PORT")
   :dbname (System/getenv "DB_NAME")
   :properties {:integratedSecurity "true"}})

(defn authenticate [username password]
  (let [db-spec (create-db-spec)
        result (jdbc/query db-spec
                           ["SELECT * FROM users WHERE username = ? AND password = ?"
                            username password])]
    (if (empty? result)
      nil
      (first result))))
