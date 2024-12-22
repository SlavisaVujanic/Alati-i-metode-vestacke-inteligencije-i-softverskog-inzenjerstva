(ns project.base
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]))

(defn create-db-spec [host dbname]
  {:dbtype "mssql"
   :host host
   :port 1433
   :dbname dbname
   :user "myuser"
   :password "clojure"})

(defn authenticate [host dbname username password]
  (let [db-spec (create-db-spec host dbname)
        result (jdbc/query db-spec
                           ["SELECT Type FROM Accounts WHERE username = ? AND password = ?" username password])]
    (if (empty? result)
      nil
      (-> (first result)
          :type
          (.trim)))))


(defn add-user [host dbname username password account-type]
  (let [db-spec (create-db-spec host dbname)]
    (jdbc/insert! db-spec :Accounts {:username username
                                     :password password
                                     :type account-type})))

(defn delete-user [host dbname username]
  (let [db-spec (create-db-spec host dbname)]
    (jdbc/delete! db-spec :Accounts ["username = ?" username])))

(defn update-user [host dbname username new-password new-account-type]
  (let [db-spec (create-db-spec host dbname)]
    (jdbc/update! db-spec :Accounts
                  {:password new-password :type new-account-type}
                  ["username = ?" username])))

(defn get-all-users [host dbname]
  (let [db-spec (create-db-spec host dbname)]
    (jdbc/query db-spec ["SELECT Username, Password, Type FROM Accounts"])))
