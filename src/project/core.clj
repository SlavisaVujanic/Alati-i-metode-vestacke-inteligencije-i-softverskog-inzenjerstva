(ns project.core
  (:gen-class) )

 ;;1.The user wants to get a parameters that makes the calculation easier for him
(defn fn1
  "kv / (kv - 1)  or  kps/(kps-1)"
  [par1]
  (if (= par1 1)
    "Divide by 0!!!"
    (if (< par1 1)
      "Not allowed value"
      (/ par1 (- par1 1)))))

(defn fn2
  "(kv-1)/kv or (kps-1)/kps"
  [par]
  (if (> par 1)
    (/ (- par 1) par)
    "Not allowed value"))

(defn fn3
  "1/(kps-1)"
  [par]
  (if (> par 1)
    (/ 1 (- par 1))
    "Not allowed value"))

(defn fn4
  "(kv-1)/2"
  [par]
  (if (> par 1)
    (/ (- par 1) 2)
    "Not allowed value"))
;;2.The user wants to get the ambient temperature depending on the flight altitude
(defn T0
  [H]
  (if (> H 0)
    (if (> H 11000)
    (- 288.15 (* 11000 0.0065))
    (- 288.15 (* H 0.0065)))
    "Invalid value of flight altitude"))

;;3.The user wants to get the density of the environment depending on the flight altitude
(defn ro0
  [H]
  (if (> H 0)
    (if (<= H 11000)
    (* 1.225 (Math/pow (- 1 (* 0.0065 (/ H 288.15))) 4.2561))
    (* 0.364 (Math/exp (/ (- 11000 H) 6330))))
    "Invalid value of flight altitude"))


 
