(ns project.base
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]))

(defn create-db-spec [host dbname]
  {:dbtype "mssql"
   :host host
   :port 1433
   :dbname dbname
   :properties {:integratedSecurity "true"}})

(defn authenticate [host dbname username password]
  (let [db-spec (create-db-spec host dbname)
        result (jdbc/query db-spec
                           ["SELECT * FROM users WHERE username = ? AND password = ?"
                            username password])]
    (if (empty? result)
      nil
      (first result))))

(defn add-user [host dbname username password account-type]
  (let [db-spec (create-db-spec host dbname)]
    (jdbc/insert! db-spec :users {:username username
                                  :password password
                                  :account_type account-type})))

(defn delete-user [host dbname username]
  (let [db-spec (create-db-spec host dbname)]
    (jdbc/delete! db-spec :users ["username = ?" username])))